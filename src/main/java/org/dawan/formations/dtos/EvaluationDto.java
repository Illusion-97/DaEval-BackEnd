package org.dawan.formations.dtos;

import java.io.Serializable;

public class EvaluationDto implements Serializable {
    private long id;
    private int version;
    private long epreuveId;
    private String epreuveTitre;
    private long etudiantId;
    private long promotionId;
    private long blocCompetencesId;
    private double note;

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

    public long getEpreuveId() {
        return epreuveId;
    }

    public void setEpreuveId(long epreuveId) {
        this.epreuveId = epreuveId;
    }

    public long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(long etudiantId) {
        this.etudiantId = etudiantId;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(long promotionId) {
        this.promotionId = promotionId;
    }

    public String getEpreuveTitre() {
        return epreuveTitre;
    }

    public void setEpreuveTitre(String epreuveTitre) {
        this.epreuveTitre = epreuveTitre;
    }

    public long getBlocCompetencesId() {
        return blocCompetencesId;
    }

    public void setBlocCompetenceId(long blocCompetenceId) {
        this.blocCompetencesId = blocCompetenceId;
    }
}
