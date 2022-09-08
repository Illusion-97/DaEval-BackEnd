package org.dawan.formations.dtos;

import java.util.List;
import java.util.Map;

public class BilanDto {
    private TitreProfessionnelDto titre;
    private PromotionDto promo;
    private List<BlocCompetencesDto> blocs;
    private Map<Long, List<CompetenceDto>> competencesParBloc;
    private List<MoyenneDto> moyennes;
    private MoyenneDto general;
    private UtilisateurDto etudiant;

    public TitreProfessionnelDto getTitre() {
        return titre;
    }

    public void setTitre(TitreProfessionnelDto titre) {
        this.titre = titre;
    }

    public Map<Long, List<CompetenceDto>> getCompetencesParBloc() {
        return competencesParBloc;
    }

    public void setCompetencesParBloc(Map<Long, List<CompetenceDto>> competencesParBloc) {
        this.competencesParBloc = competencesParBloc;
    }

    public List<BlocCompetencesDto> getBlocs() {
        return blocs;
    }

    public void setBlocs(List<BlocCompetencesDto> blocs) {
        this.blocs = blocs;
    }

    public List<MoyenneDto> getMoyennes() {
        return moyennes;
    }

    public void setMoyennes(List<MoyenneDto> moyennes) {
        this.moyennes = moyennes;
    }

    public MoyenneDto getGeneral() {
        return general;
    }

    public void setGeneral(MoyenneDto general) {
        this.general = general;
    }

    public UtilisateurDto getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(UtilisateurDto etudiant) {
        this.etudiant = etudiant;
    }

    public PromotionDto getPromo() {
        return promo;
    }

    public void setPromo(PromotionDto promo) {
        this.promo = promo;
    }
}
