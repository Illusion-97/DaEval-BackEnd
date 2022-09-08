package org.dawan.formations.dtos.index;

public class JsonStructureDto {
    private final String typeName;
    private final String structure;

    public JsonStructureDto(String typeName, String sctructure) {
        this.typeName = typeName;
        this.structure = sctructure;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getStructure() {
        return structure;
    }
}
