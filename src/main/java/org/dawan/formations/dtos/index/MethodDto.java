package org.dawan.formations.dtos.index;

import java.util.List;
import java.util.Objects;

public class MethodDto {
    private final String httpMethod;
    private final String name;
    private final List<ParamDto> params;

    public MethodDto(String httpMethod, String name, List<ParamDto> params) {
        this.httpMethod = httpMethod;
        this.name = name;
        this.params = params;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getName() {
        return name;
    }

    public List<ParamDto> getParams() {
        return params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodDto methodDto = (MethodDto) o;
        return httpMethod.equals(methodDto.httpMethod) && name.equals(methodDto.name) && params.equals(methodDto.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpMethod, name, params);
    }
}
