package org.dawan.formations.dtos;

import org.dawan.formations.models.Niveau;

import java.io.Serializable;

public class PositionDto implements Serializable {
    private long id;
    private int version;
    private long competenceId;
    private String competenceTitre;
    private long etudiantId;
    private long promotionId;
    private long blocCompetencesId;
    private Niveau avant;
    private Niveau apres;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(long etudiantId) {
        this.etudiantId = etudiantId;
    }

    public long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(long promotionId) {
        this.promotionId = promotionId;
    }

    public long getBlocCompetencesId() {
        return blocCompetencesId;
    }

    public void setBlocCompetencesId(long blocCompetencesId) {
        this.blocCompetencesId = blocCompetencesId;
    }

    public Niveau getAvant() {
        return avant;
    }

    public void setAvant(Niveau avant) {
        this.avant = avant;
    }

    public Niveau getApres() {
        return apres;
    }

    public void setApres(Niveau apres) {
        this.apres = apres;
    }

    public long getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(long competenceId) {
        this.competenceId = competenceId;
    }

    public String getCompetenceTitre() {
        return competenceTitre;
    }

    public void setCompetenceTitre(String competenceTitre) {
        this.competenceTitre = competenceTitre;
    }
}
