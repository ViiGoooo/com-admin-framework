package com.admin.framework.component.utils;




import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 *  反射工具
 * @author ZSW
 * @date 2018/11/20
 */
public class ReflectUtil {

    /**
     * 是否开启驼峰转下划线
     */
    public static boolean hump_to_underline = false;
    public static boolean underline_to_hump = false;


    /**
     * 获取所有的成员变量
     * @param clz
     * @return
     */
    public static List<String> getAllField(Class<?> clz){
        Field[] fields = clz.getDeclaredFields();
        List<String> result = new ArrayList<>();
        for(Field field:fields){
            String name = field.getName();
            result.add(name);
        }
        return result;
    }


    /**
     * map转对象
     * @param list
     */
    public static<T> List<T> mapToBean(List<Map> list,Class<T> clz){
        List<T> result = new ArrayList<>();
        for(Map map : list){
            T t = mapToBean(map, clz);
            result.add(t);
        }
        return result;
    }

    /**
     * map转对象
     * @param map
     */
    public static<T> T mapToBean(Map map,Class<T> clz){
        Field[] fields = clz.getFields();
        if(ArrayUtil.isEmpty(fields)){
            fields = clz.getDeclaredFields();
        }
        try {
            T t = clz.newInstance();
            for(Field field : fields) {
                String fieldName = field.getName();
                Object value = getValue(map,fieldName);
                if (value == null || "".equals(value)) {
                    continue;
                }
                Class<?> type = field.getType();
                String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
                Method method = clz.getMethod("set" + methodName, type);
                Object v = getValue(value, type);
                method.invoke(t, v);
            }
            return t;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 实体类转map
     * @param target
     * @return
     */
    public static List<Map> beanToMap(List<?> target){
        List<Map> result = new ArrayList<>();
        for(Object o:target){
            Map map = beanToMap(o);
            result.add(map);
        }
        return result;
    }

    /**
     * 实体类转map
     * @param object
     * @return
     */
    public static Map beanToMap(Object object){
        Map result = new HashMap(16);
        Class clz = object.getClass();
        Field[] fields = clz.getFields();
        if(ArrayUtil.isEmpty(fields)){
            fields = clz.getDeclaredFields();
        }
        for(Field field : fields){
            String fieldName = field.getName();
            String  methodName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1,fieldName.length());
            try {
                Method method = clz.getMethod("get"+methodName);
                Object invoke = method.invoke(object);
                if(hump_to_underline){
                    result.put(StringUtil.humpToUnderline(fieldName),invoke);
                }else{
                    result.put(fieldName,invoke);
                }
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
        return result;
    }


    /**
     * 对象拷贝
     * @param source
     * @param targetClz
     * @param <S>
     * @param <T>
     * @param nullAble 是否忽略为null的属性
     * @return
     */
    public static<S,T> T copy(S source,Class<T> targetClz,boolean nullAble) throws Exception {
        Field[] targetFields = targetClz.getFields();
        if(ArrayUtil.isEmpty(targetFields)){
            targetFields = targetClz.getDeclaredFields();
        }
        Class sourceClass = source.getClass();
        try {
            T target = targetClz.newInstance();
            for(Field field:targetFields){
                Class<?> type = field.getType();
                String fieldName = field.getName();
                String  methodName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
                try {
                    Method getMethod = sourceClass.getMethod("get"+methodName);
                    Object value = getMethod.invoke(source);

                    if(nullAble && value == null){
                        continue;
                    }

                    Method method = targetClz.getMethod("set" + methodName, type);
                    Object v = getValue(value, type);
                    method.invoke(target, v);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    continue;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            return target;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new Exception("拷贝失败");
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new Exception("拷贝失败");
        }
    }



    /**
     * 获取值
     * @param map
     * @param fieldName
     * @return
     */
    private static Object getValue(Map map , String fieldName){
        Object value = map.get(fieldName);
        if (value == null) {
            value = map.get(StringUtil.humpToUnderline(fieldName));
            if (value == null) {
                value = map.get(StringUtil.underToHump(fieldName));
            }
        }
        return value;
    }



    private static<T> T getValue(Object value,Class<T> clz){
        String simpleName = clz.getSimpleName();
        if(Integer.class.getSimpleName().equals(simpleName) || int.class.getSimpleName().equals(simpleName)){
            return (T) Integer.valueOf(value.toString());
        }
        if(String.class.getSimpleName().equals(simpleName)){
            return (T) String.valueOf(value.toString());
        }
        if(Byte.class.getSimpleName().equals(simpleName) || byte.class.getSimpleName().equals(simpleName)){
            return (T) value.toString().getBytes();
        }
        if(Long.class.getSimpleName().equals(simpleName) || long.class.getSimpleName().equals(simpleName)){
            return (T) Long.valueOf(value.toString());
        }
        if(Double.class.getSimpleName().equals(simpleName) || double.class.getSimpleName().equals(simpleName)){
            return (T) Double.valueOf(value.toString());
        }
        if(Float.class.getSimpleName().equals(simpleName) || float.class.getSimpleName().equals(simpleName)){
            return (T) Float.valueOf(value.toString());
        }
        if(Character.class.getSimpleName().equals(simpleName) || char.class.getSimpleName().equals(simpleName)){
            char[] chars = value.toString().toCharArray();
            return (T) Character.valueOf(chars[0]);
        }
        if(Short.class.getSimpleName().equals(simpleName) || short.class.getSimpleName().equals(simpleName)){
            return (T) Short.valueOf(value.toString());
        }
        if(Boolean.class.getSimpleName().equals(simpleName) || boolean.class.getSimpleName().equals(simpleName)){
            return (T) Boolean.valueOf(value.toString());
        }
        if(Date.class.getSimpleName().equals(simpleName)){
            return (T) DateUtil.format(value.toString(),DateUtil.USUAL_FORMAT);
        }
        if(BigDecimal.class.getSimpleName().equals(simpleName)){
            return (T) new BigDecimal(value.toString());
        }
        return null;
    }

    private static boolean isBaseType(Class className){
        if (className.equals(String.class)) {
            return true;
        }
        return className.equals(Integer.class) ||
                className.equals(int.class) ||
                className.equals(Byte.class) ||
                className.equals(byte.class) ||
                className.equals(Long.class) ||
                className.equals(long.class) ||
                className.equals(Double.class) ||
                className.equals(double.class) ||
                className.equals(Float.class) ||
                className.equals(float.class) ||
                className.equals(Character.class) ||
                className.equals(char.class) ||
                className.equals(Short.class) ||
                className.equals(short.class) ||
                className.equals(Boolean.class) ||
                className.equals(boolean.class);
    }

}
