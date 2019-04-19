package com.youxiunanren.yxnr.rs.core;

import com.youxiunanren.yxnr.model.Entity;
import com.youxiunanren.yxnr.model.annotation.DtoRequired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

public class ResponseOptimizer {

    private ResponseOptimizer(){}

    public static LinkedHashMap<String, Object> optimize(Entity entity){
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
