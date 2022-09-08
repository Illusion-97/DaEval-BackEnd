package org.dawan.formations.dtos.index;

import java.util.Objects;

public class ParamDto {
    private final Class<?> type;
    private final  String typeName;
    private final String paramName;

    public ParamDto(Class<?> type, String paramName) {
        this.type = type;
        this.typeName = type.getSimpleName();
        this.paramName = paramName;
    }

    public Class<?> getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getParamName() {
        return paramName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParamDto paramDto = (ParamDto) o;
        return typeName.contentEquals(paramDto.typeName) && paramName.contentEquals(paramDto.paramName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName, paramName);
    }
}
