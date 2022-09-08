package org.dawan.formations.dtos.index;

import java.util.List;

public class IndexDto {
    private final String appName;
    private final List<ServiceDto> services ;
    private final List<JsonStructureDto> jsonStructures;

    public IndexDto(String appName, List<ServiceDto> services, List<JsonStructureDto> jsonStructures) {
        this.appName = appName;
        this.services = services;
        this.jsonStructures = jsonStructures;
    }

    public List<ServiceDto> getServices() {
        return services;
    }

    public String getAppName() {
        return appName;
    }

    public List<JsonStructureDto> getJsonStructures() {
        return jsonStructures;
    }
}
