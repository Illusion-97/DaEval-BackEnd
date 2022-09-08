package org.dawan.formations.tools;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RestTemplateTool {

    private static final RestTemplate restTemplate = new RestTemplate();

    public static <T> T getObjectFromUrl(String url, Class<T> type) {
        return getObject(new JSONObject(restTemplate.getForEntity(url, String.class).getBody()).toMap(), type);
    }

    public static <T> List<T> getListFromUrl(String url, Class<T> type) {
        return new JSONArray(restTemplate.getForEntity(url, String.class).getBody())
                .toList().stream().map(o -> (Map<String, Object>) o)
                .map(m -> getObject(m, type)).collect(Collectors.toList());
    }

    private static <T> T getObject(Map<String, Object> map, Class<T> type) {
        T obj = null;
        if (type.isArray()) {
            return (T) map.values().stream().map(o -> (Map<String, Object>) o)
                    .map(m -> getObject(m, type.getComponentType())).toArray();
        } else {
            try {
                obj = type.newInstance();
                PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(obj);
                for (Field field : type.getDeclaredFields()) {
                    Object value = map.get(field.getName());
                    if (value != null) setValue(myAccessor, value, field);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    private static void setValue(PropertyAccessor myAccessor, Object origin, Field field) throws InstantiationException, IllegalAccessException {
        if (origin instanceof Map) {
            Class<?> sub = field.getType();
            if (sub == Map.class) {
                myAccessor.setPropertyValue(field.getName(), origin);
                return;
            }
            Map<String, Object> mapOrigin = (Map<String, Object>) origin;
            Object subDest = sub.newInstance();
            PropertyAccessor subAcess = PropertyAccessorFactory.forBeanPropertyAccess(subDest);
            for (Field subField : sub.getDeclaredFields()) {
                Object value = mapOrigin.get(subField.getName());
                if (value != null) setValue(subAcess, value, subField);
            }
            myAccessor.setPropertyValue(field.getName(), subDest);
        } else {
            if ((origin instanceof List)) {
                myAccessor.setPropertyValue(field.getName(), ((List<Map<String, Object>>) origin).stream()
                        .map(m -> getObject(m, ((Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0])))
                        .collect(Collectors.toList()));
            } else {
                if (origin != null) myAccessor.setPropertyValue(field.getName(), origin);
            }
        }
    }
}
