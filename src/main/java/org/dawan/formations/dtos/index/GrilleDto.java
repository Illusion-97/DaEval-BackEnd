package org.dawan.formations.dtos.index;

import org.dawan.formations.dtos.*;

import java.util.List;
import java.util.Map;

public class GrilleDto {
    private TitreProfessionnelDto titre;
    private PromotionDto promo;
    private List<BlocCompetencesDto> blocs;
    private Map<Long, List<CompetenceDto>> competencesParBloc;
    private List<PositionDto> positions;
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

    public List<PositionDto> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionDto> positions) {
        this.positions = positions;
    }
}
