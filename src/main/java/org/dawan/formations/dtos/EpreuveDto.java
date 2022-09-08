package org.dawan.formations.dtos;

import org.dawan.formations.models.TYPE_EPREUVE;

import java.io.Serializable;

public class EpreuveDto implements Serializable {
    private long id;
    private int version;
    private String titre;
    private String description;
    private TYPE_EPREUVE type;
    private long blocCompetencesId;
    private String blocCompetencesTitre;

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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TYPE_EPREUVE getType() {
        return type;
    }

    public void setType(TYPE_EPREUVE type) {
        this.type = type;
    }

    public long getBlocCompetencesId() {
        return blocCompetencesId;
    }

    public void setBlocCompetencesId(long blocCompetencesId) {
        this.blocCompetencesId = blocCompetencesId;
    }

    public String getBlocCompetencesTitre() {
        return blocCompetencesTitre;
    }

    public void setBlocCompetencesTitre(String blocCompetencesTitre) {
        this.blocCompetencesTitre = blocCompetencesTitre;
    }
}
