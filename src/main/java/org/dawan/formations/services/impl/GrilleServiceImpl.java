package org.dawan.formations.services.impl;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import freemarker.template.TemplateException;
import org.dawan.formations.dtos.*;
import org.dawan.formations.dtos.index.GrilleDto;
import org.dawan.formations.services.*;
import org.dawan.formations.tools.FreeMarkerTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class GrilleServiceImpl {

    private final TitreProfessionnelService titreProfessionnelService;
    private final CompetenceService competenceService;
    private final PromotionService promotionService;
    private final BlocCompetencesService blocCompetencesService;
    private final PositionServiceImpl positionService;
    private final EvaluationService evaluationService;
    private final EtudiantService etudiantService;
    private final UtilisateurService utilisateurService;
    @Value("${app.storagefolder}")
    private String storageFolder;

    public GrilleServiceImpl(TitreProfessionnelService titreProfessionnelService, CompetenceService competenceService, PromotionService promotionService, BlocCompetencesService blocCompetencesService, EpreuveService epreuveService, PositionServiceImpl positionService, EvaluationService evaluationService, EtudiantService etudiantService, UtilisateurService utilisateurService) {
        this.titreProfessionnelService = titreProfessionnelService;
        this.competenceService = competenceService;
        this.promotionService = promotionService;
        this.blocCompetencesService = blocCompetencesService;
        this.positionService = positionService;
        this.evaluationService = evaluationService;
        this.etudiantService = etudiantService;
        this.utilisateurService = utilisateurService;
    }

    public String GETgenerate(long etudiantId, long promotionId) throws IOException, TemplateException {
        GrilleDto grille = new GrilleDto();
        PromotionDto promo = promotionService.GETById(promotionId);
        grille.setPromo(promo);
        grille.setTitre(titreProfessionnelService.GETById(promo.getTitreProfessionnelId()));
        grille.setBlocs(blocCompetencesService.GETfindByTitreProId(grille.getTitre().getId()));
        grille.setCompetencesParBloc(
                grille.getBlocs().stream()
                        .map(blocCompetencesDto -> competenceService.GETfindByBlocCompetences_Id(blocCompetencesDto.getId()).stream())
                        .reduce(Stream::concat).orElseThrow(RuntimeException::new)
                        .collect(Collectors.groupingBy(CompetenceDto::getBlocCompetencesId))
        );
        grille.setEtudiant(utilisateurService.GETById(etudiantService.GETById(etudiantId).getUtilisateurId()));
        grille.setPositions(positionService.getForGrille(etudiantId,promotionId));
        String result = FreeMarkerTool.getResult(this.getClass(),"Grille.ftl", grille);
        String outputPdf = storageFolder
                + "/titrepro-" + grille.getTitre().getId() + "-"
                + grille.getEtudiant().getNom() + " " +grille.getEtudiant().getPrenom() + " "
                + ".pdf";

        //création du outputStream pour le stockage
        OutputStream os = new BufferedOutputStream(new FileOutputStream(outputPdf));

        //objet permettant de builder le pdf
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withUri(outputPdf);
        builder.toStream(os);

        builder.withHtmlContent(result,"http://localhost:8080/grille/");
        builder.run();

        os.close();
        return result;
    }
}
