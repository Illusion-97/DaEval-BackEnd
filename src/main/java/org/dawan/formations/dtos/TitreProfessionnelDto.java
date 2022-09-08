package org.dawan.formations.dtos;

import java.io.Serializable;
import java.util.Objects;

public class TitreProfessionnelDto implements Serializable {
    private long id;
    private int version;
    private String titre;
    private String slug;
    private String description;
    private String objectives;


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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitreProfessionnelDto that = (TitreProfessionnelDto) o;
        return slug.contentEquals(that.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slug);
    }
}
