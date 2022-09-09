package org.dawan.formations.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.dawan.formations.dtos.index.MethodDto;
import org.dawan.formations.dtos.index.ParamDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflecTool {

    private ReflecTool() {}

    private static final String[] methods = {"GET","POST","PUT","DELETE"};
    public static List<MethodDto> test(Class<?> serviceClass, List<MethodDto> exclusion)
    {
        return Arrays.stream(serviceClass.getMethods())
                .filter(m -> Arrays.stream(methods).anyMatch(method -> m.getName().contains(method))) // Seek formalized method Name
                .map(method -> {
                    String[] formatedName = formatName(method.getName()); // Format Method Name for ulterior usage
                    return new MethodDto(formatedName[0],formatedName[1], // 0: Method , 1: Formalized name
                    Arrays.stream(method.getParameters()).map(param ->new ParamDto(param.getType(),param.getName())).collect(Collectors.toList()));
                }).filter(m -> !exclusion.contains(m)).collect(Collectors.toList());
    }
    private static String[] formatName(String name)
    {
        return Arrays.stream(methods).filter(name::contains)
                .map(m -> new String[]{m,name.replace(m,"")}).findFirst()
                .orElse(new String[]{"",name});

    }

    public static String getJsonStructure(Class<?> type)
    {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            //Try to create an Object with default values, then stringnify the structure
            return ow.writeValueAsString(type.newInstance());
        } catch (Exception e) {
            return null;
        }
    }
}
