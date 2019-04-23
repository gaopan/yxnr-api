package com.youxiunanren.yxnr.rs.core;

import com.youxiunanren.yxnr.model.DataTransferEntity;
import com.youxiunanren.yxnr.model.annotation.DtoRequired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResponseOptimizer {

    private ResponseOptimizer(){}

    public static <T> List<Map<String, Object>> optimize(Class<T> tClass, List<T> entities){
        if(entities == null || entities.size() < 1) return new ArrayList<>();
        int size = entities.size();
        List<Map<String, Object>> results = new ArrayList<>();
        for(int i=0; i < size; i++) {
            T t = entities.get(i);
            if(t instanceof DataTransferEntity) {
                results.add(optimize((DataTransferEntity) t));
            }
        }
        return results;
    }

    public static LinkedHashMap<String, Object> optimize(DataTransferEntity entity){
        if(entity == null) return null;
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();

        Field[] fields = entity.getClass().getDeclaredFields();
        Method[] methods = entity.getClass().getDeclaredMethods();

        for(Field field : fields) {
            if(field.getDeclaredAnnotation(DtoRequired.class) != null) {
                for(Method method : methods) {
                    String methodName = method.getName();
                    if(methodName.startsWith("get") && methodName.substring(3).toLowerCase().equals(field.getName().toLowerCase())) {
                        try {
                            result.put(field.getName(), method.invoke(entity));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }

        return result;
    }
}
