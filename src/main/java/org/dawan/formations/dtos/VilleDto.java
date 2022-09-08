package org.dawan.formations.dtos;

import java.io.Serializable;
import java.util.Objects;

public class VilleDto implements Serializable {
    private long id;
    private int version;
    private String name;
    private String slug;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VilleDto villeDto = (VilleDto) o;
        return slug.equals(villeDto.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slug);
    }
}
