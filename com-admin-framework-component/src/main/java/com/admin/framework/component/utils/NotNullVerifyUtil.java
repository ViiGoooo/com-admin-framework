package com.admin.framework.component.utils;

import com.admin.framework.component.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author ZSW
 * @date 2019/4/5
 */
public class NotNullVerifyUtil {
    public static List<String> result = new ArrayList<>();


    /**
     * 非空注解校验
     * @param object
     * @return
     */
    public static List<String> verify(Object object){
        NotNullVerifyUtil.result = new ArrayList<>();
        Class<?> clz = object.getClass();
        Field[] publicFields = clz.getFields();
        Field[] privateFields = clz.getDeclaredFields();

        if(ArrayUtil.isEmpty(publicFields) && ArrayUtil.isEmpty(privateFields)){
            return result;
        }
        Field[] fields = new Field[0];
        if(!ArrayUtil.isEmpty(publicFields)){
            fields = ArrayUtil.merge(publicFields, privateFields);
        }else if(!ArrayUtil.isEmpty(privateFields)){
            fields = ArrayUtil.merge(privateFields, publicFields);
        }

        for(Field field : fields){
            NotNull annotation = field.getAnnotation(NotNull.class);
            if(annotation == null){
                continue;
            }
            String fieldName = field.getName();
            String methodName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1,fieldName.length());
            try {
                Method method = clz.getMethod("get"+methodName);
                Object invoke = method.invoke(object);
                check(fieldName,invoke,annotation);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
        return result;
    }



    /**
     * 校验
     * @param key
     * @param value
     * @param annotation
     */
    public static void check(String key,Object value,NotNull annotation){
        isEmpty(key,value,annotation);
        maxLength(key,value,annotation);
        minLength(key,value,annotation);
        regular(key,value,annotation);
    }

    /**
     * 校验最大长度
     * @param value
     * @param annotation
     */
    public static void maxLength(String key,Object value,NotNull annotation){
        if(annotation.nullAble()){
            return;
        }
        if(annotation.maxLength() == 0){
            return;
        }
        if(value == null){
            addMsg(key,annotation);
            return;
        }
        int i = annotation.maxLength();
        if(value.toString().length() > i){
            addMsg(key,annotation);
        }
    }

    /**
     * 校验最小长度
     * @param value
     * @param annotation
     */
    public static void minLength(String key,Object value,NotNull annotation){
        if(annotation.nullAble()){
            return;
        }
        if(annotation.minLength() == 0){
            return;
        }
        if(value == null){
            addMsg(key,annotation);
            return;
        }
        int i = annotation.maxLength();
        if(value.toString().length() < i){
            addMsg(key,annotation);
        }
    }


    /**
     * 校验非空
     * @param value
     * @return
     */
    public static void isEmpty(String key,Object value,NotNull annotation){
        if(annotation.nullAble()){
            return;
        }
        if(value == null || "".equals(value)){
            addMsg(key,annotation);
        }
    }


    /**
     * 校验正则
     * @param key 参数名
     * @param value 参数值
     * @param annotation 消息
     */
    public static void regular(String key,Object value,NotNull annotation){
        if(annotation.nullAble()){
            return;
        }
        String[] regular = annotation.regular();
        if(ArrayUtil.isEmpty(regular)){
            return;
        }
        for(String r:regular){
            boolean matches = Pattern.matches(r, value.toString());
            if(!matches){
                addMsg(key,annotation);
            }
        }
    }


    /**
     * 添加参数
     * @param key
     * @param annotation
     */
    private static void addMsg(String key,NotNull annotation){
        String message = annotation.message();
        if(StringUtil.isEmpty(message)){
            result.add("参数【" + key + "】不是合法参数");
        }else{
            result.add(message);
        }
    }


    public static void main(String[] args) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

        boolean matches = Pattern.matches(RegexUtil.MOBILE, "13455555555");
        System.out.println(matches);
    }

}
