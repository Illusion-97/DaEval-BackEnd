package org.dawan.formations.dtos.index;

import java.util.List;

public class ServiceDto {
    private final String name;
    private final List<MethodDto> methods;
    private final String dtoName;
    private final String objectStructure;

    public ServiceDto(String name, List<MethodDto> methods, String dtoName, String objectStructure) {
        this.name = name;
        this.methods = methods;
        this.dtoName = dtoName;
        this.objectStructure = objectStructure;
    }

    public String getName() {
        return name;
    }

    public List<MethodDto> getMethods() {
        return methods;
    }

    public String getObjectStructure() {
        return objectStructure;
    }

    public String getDtoName() {
        return dtoName;
    }
}
