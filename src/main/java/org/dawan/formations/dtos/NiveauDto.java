package org.dawan.formations.dtos;

import org.dawan.formations.models.Niveau;

public class NiveauDto {
    private Niveau id;
    private String name;

    public NiveauDto(Niveau id, String name) {
        this.id = id;
        this.name = name;
    }

    public Niveau getId() {
        return id;
    }

    public void setId(Niveau id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
