package org.dawan.formations.dtos;

public class EnumDto {
    private Enum<?> id;
    private String name;

    public EnumDto(Enum<?> id, String name) {
        this.id = id;
        this.name = name;
    }

    public Enum<?> getId() {
        return id;
    }

    public void setId(Enum<?> id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
