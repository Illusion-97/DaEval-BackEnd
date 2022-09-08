package org.dawan.formations.services;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dawan.formations.dtos.index.IndexDto;
import org.dawan.formations.dtos.index.JsonStructureDto;
import org.dawan.formations.dtos.index.MethodDto;
import org.dawan.formations.dtos.index.ServiceDto;
import org.dawan.formations.tools.FreeMarkerTool;
import org.dawan.formations.tools.ReflecTool;
import org.dawan.formations.tools.WrapperTool;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HandlerService {
    private final ApplicationContext context;
    private static final String SERVICE_SUFFIX = "ServiceImpl";

    @Autowired
    public HandlerService(ApplicationContext context) {
        this.context = context;
        WrapperTool.initWrappers();
    }

    public String index() {
        List<ServiceDto> services = Arrays.stream(context.getBeanDefinitionNames()).filter(b -> b.contains(SERVICE_SUFFIX))
                .map(n ->
                {
                    Object service = context.getBean(n);
                    String dtoName = "No Dto";
                    String dtoStructure = "No DtoStructure";
                    try {

                        GenericService<?,?,?> genericService = (GenericService<?,?,?>)service;
                        dtoName = genericService.getDtoName();
                        dtoStructure = genericService.getDtoStructure();
                    } catch (Exception ignored) {
                    }
                    return new ServiceDto(n.replace(SERVICE_SUFFIX, ""),
                        ReflecTool.test(service.getClass(), new ArrayList<>()), dtoName, dtoStructure);
                }).collect(Collectors.toList());
        List<JsonStructureDto> structure = services.stream()
                .map(serviceDto -> serviceDto.getMethods().stream()
                        .map(MethodDto::getParams).flatMap(List::stream).distinct()
                        .map(type -> new JsonStructureDto(type.getTypeName(), ReflecTool.getJsonStructure(type.getType())))
                        .filter(struct -> struct.getStructure()!=null)
                        .collect(Collectors.toList()))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        try {
            return FreeMarkerTool.getResult(this.getClass(),"Index.ftl",new IndexDto(context.getId(), services, structure));
        } catch (Exception e)
        {
            return "Somthing Went Wrong";
        }
    }

    public Object handle(String httpMethod, Map<String, String> pathArgs, Map<String,String> reqArgs, String body)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException,
            NoSuchBeanDefinitionException {

            String serviceName = pathArgs.get("service") + SERVICE_SUFFIX;
            Object service = context.getBean(serviceName);
            Method method = Arrays.stream(service.getClass().getMethods())
                    .filter(m -> m.getName().contentEquals( httpMethod + pathArgs.get("method")))
                    .findFirst().orElseThrow(NoSuchMethodException::new);
            return method.invoke(service,getArgs(method,body,reqArgs));
    }

    private Object[] getArgs(Method method, String body, Map<String,String> reqArgs) {
        return Arrays.stream(method.getParameters()).map( parameter ->
        {
            Class<?> type = parameter.getType();
            try
            {
                return Objects.requireNonNull(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(body,type));
            }
            catch (Exception ex)
            {
                try
                {
                    return WrapperTool.getWrappedObject(type,reqArgs.get(parameter.getName()));
                } catch (Exception e)
                {
                    return null;
                }
            }
        }).toArray();
    }
}
