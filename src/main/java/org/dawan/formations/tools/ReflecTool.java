package org.dawan.formations.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.dawan.formations.dtos.index.MethodDto;
import org.dawan.formations.dtos.index.ParamDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflecTool {
    public static List<MethodDto> test(Class<?> serviceClass, List<MethodDto> exclusion)
    {
        return Arrays.stream(serviceClass.getMethods())
                .filter(m -> m.getName().contains("GET")
                        || m.getName().contains("POST")
                        || m.getName().contains("PUT")
                        || m.getName().contains("DELETE")).map(method ->
                {
                    String[] formatedName = formatName(method.getName());
                    return new MethodDto(formatedName[0],formatedName[1],
                    Arrays.stream(method.getParameters()).map(param ->new ParamDto(param.getType(),param.getName())).collect(Collectors.toList()));
                }).filter(m -> !exclusion.contains(m)).collect(Collectors.toList());
    }
    private static String[] formatName(String name)
    {
        String[] methods = {"GET","POST","PUT","DELETE"};
        return Arrays.stream(methods).filter(name::contains)
                .map(m -> new String[]{m,name.replace(m,"")}).findFirst()
                .orElse(new String[]{"",name});

    }

    public static String getJsonStructure(Class<?> type)
    {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(type.newInstance());
        } catch (Exception e) {
            return null;
        }
    }
}
