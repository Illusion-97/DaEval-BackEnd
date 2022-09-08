package org.dawan.formations.dtos;

import java.io.Serializable;

public class BlocCompetencesDto implements Serializable {
    private long id;
    private int version;
    private String titre;
    private String description;
    private long titreProfessionnelId;

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

    public long getTitreProfessionnelId() {
        return titreProfessionnelId;
    }

    public void setTitreProfessionnelId(long titreProfessionnelId) {
        this.titreProfessionnelId = titreProfessionnelId;
    }
}
