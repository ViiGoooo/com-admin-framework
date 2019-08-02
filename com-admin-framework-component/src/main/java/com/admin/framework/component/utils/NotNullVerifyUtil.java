package com.admin.framework.component.utils;

import com.admin.framework.component.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author ZSW
 * @date 2019/4/5
 */
public class NotNullVerifyUtil {

    /**
     * 非空注解校验
     * @param object
     * @return
     */
    public static String verify(Object object){
        Class<?> clz = object.getClass();
        Field[] fields = clz.getFields();
        if(ArrayUtil.isEmpty(fields)){
            fields = clz.getDeclaredFields();
        }
        for(Field field : fields){
            NotNull annotation = field.getAnnotation(NotNull.class);
            if(annotation == null){
                continue;
            }
            String message = annotation.message();
            String fieldName = field.getName();
            String  methodName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1,fieldName.length());
            try {
                Method method = clz.getMethod("get"+methodName);
                Object invoke = method.invoke(object);
                if(invoke == null || "".equals(invoke)){
                    return message;
                }
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
        return "";
    }

}
