package org.dawan.formations.services.impl;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import freemarker.template.TemplateException;
import org.dawan.formations.dtos.*;
import org.dawan.formations.services.*;
import org.dawan.formations.tools.FreeMarkerTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.*;

@Component
public class BilanServiceImpl implements BilanService {

    private final TitreProfessionnelService titreProfessionnelService;
    private final CompetenceService competenceService;
    private final PromotionService promotionService;
    private final BlocCompetencesService blocCompetencesService;
    private final EpreuveService epreuveService;
    private final EvaluationService evaluationService;
    private final EtudiantService etudiantService;
    private final UtilisateurService utilisateurService;
    @Value("${app.storagefolder}")
    private String storageFolder;

    public BilanServiceImpl(TitreProfessionnelService titreProfessionnelService, CompetenceService competenceService, PromotionService promotionService, BlocCompetencesService blocCompetencesService, EpreuveService epreuveService, EvaluationService evaluationService, EtudiantService etudiantService, UtilisateurService utilisateurService) {
        this.titreProfessionnelService = titreProfessionnelService;
        this.competenceService = competenceService;
        this.promotionService = promotionService;
        this.blocCompetencesService = blocCompetencesService;
        this.epreuveService = epreuveService;
        this.evaluationService = evaluationService;
        this.etudiantService = etudiantService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public int generate3To5EvalPerStudientPerBlocForPromo(long promotionId) {
        int j = new Random().nextInt(2);
        PromotionDto promo = promotionService.GETById(promotionId);
        return blocCompetencesService.GETfindByTitreProId(promo.getTitreProfessionnelId()).stream().map(blocCompetencesDto ->
                epreuveService.GETfindByBlocCompetences_Id(blocCompetencesDto.getId()).stream().map(epreuveDto ->
                {
                    int nbEval = 0;
                    for(int i = j; i< 5;i++)
                    {
                        for (Long etudiantId:promo.getEtudiantsId()) {
                            EvaluationDto eval = new EvaluationDto();
                            eval.setEpreuveId(epreuveDto.getId());
                            eval.setEtudiantId(etudiantId);
                            eval.setNote(Math.round(new Random().nextDouble()*2000)/100.0);
                            evaluationService.POSTSave(eval);
                            nbEval++;
                        }
                    }
                    return nbEval;
                })).reduce(Stream.of(0), Stream::concat).reduce(0,Integer::sum);
    }

    @Override
    public String GETgenerate(long etudiantId, long promotionId) throws IOException, TemplateException {
        BilanDto bilan = new BilanDto();
        PromotionDto promo = promotionService.GETById(promotionId);
        bilan.setPromo(promo);
        bilan.setTitre(titreProfessionnelService.GETById(promo.getTitreProfessionnelId()));
        bilan.setBlocs(blocCompetencesService.GETfindByTitreProId(bilan.getTitre().getId()));
        bilan.setCompetencesParBloc(
                bilan.getBlocs().stream()
                        .map(blocCompetencesDto -> competenceService.GETfindByBlocCompetences_Id(blocCompetencesDto.getId()).stream())
                        .reduce(Stream::concat).orElseThrow(RuntimeException::new)
                        .collect(Collectors.groupingBy(CompetenceDto::getBlocCompetencesId))
        );
        bilan.setMoyennes(
                bilan.getBlocs().stream().map( bloc ->
                        new MoyenneDto(evaluationService.GETavgByEtudiantAndPromotionAndBloc(etudiantId,promotionId,bloc.getId()),
                                evaluationService.GETavgByEtudiantAndPromotionAndBloc(null,promotionId,bloc.getId()),
                                bloc.getId())
                ).collect(Collectors.toList())
        );
        bilan.setEtudiant(utilisateurService.GETById(etudiantService.GETById(etudiantId).getUtilisateurId()));
        bilan.setGeneral(new MoyenneDto(evaluationService.GETavgByEtudiantAndPromotionAndBloc(etudiantId,promotionId,null),
                evaluationService.GETavgByEtudiantAndPromotionAndBloc(null,promotionId,null),
                -1));
        String result = FreeMarkerTool.getResult(this.getClass(),"Bilan.ftl", bilan);
        String outputPdf = storageFolder
                + "/titrepro-" + bilan.getTitre().getId() + "-"
                + bilan.getEtudiant().getNom() + " " +bilan.getEtudiant().getPrenom() + " "
                + ".pdf";

        //création du outputStream pour le stockage
        OutputStream os = new BufferedOutputStream(new FileOutputStream(outputPdf));

        //objet permettant de builder le pdf
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withUri(outputPdf);
        builder.toStream(os);

        builder.withHtmlContent(result,"http://localhost:8080/bilan/");
        builder.run();

        os.close();
        return result;
    }
}
