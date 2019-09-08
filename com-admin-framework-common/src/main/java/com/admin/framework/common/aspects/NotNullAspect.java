package com.admin.framework.common.aspects;

import com.admin.framework.common.entity.Resp;
import com.admin.framework.common.exception.BusinessException;
import com.admin.framework.component.annotations.NotNull;
import com.admin.framework.component.annotations.ParamAnnotation;
import com.admin.framework.component.annotations.ParamField;
import com.admin.framework.component.utils.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


/**
 *
 * @author ZSW
 * @date 2018/11/21
 */
@Aspect
@Component
public class NotNullAspect {

    private static final Class<ParamAnnotation> PARAM_ANNOTATION_CLASS = ParamAnnotation.class;

    private static final Class<NotNull> NOT_NULL_ANNOTATION_CLASS = NotNull.class;

    private static final String MAP_TYPE_NAME = "java.util.LinkedHashMap";

    private static final String RETURN_TYPE_NAME = "Resp";



    @Pointcut(value = "@annotation(com.admin.framework.component.annotations.ParamAnnotation)")
    public void pointcut() {
    }

    List<String> errorMsg = new ArrayList<>();
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            errorMsg = new ArrayList<>();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Object target = joinPoint.getTarget();
            Method method = target.getClass().getMethod(signature.getName(), signature.getParameterTypes());
            Object[] args = joinPoint.getArgs();
            Parameter[] parameters = method.getParameters();

            Class<?> returnType = method.getReturnType();
            String returnTypeName = returnType.getSimpleName();
            ParamAnnotation methodAnno = method.getAnnotation(PARAM_ANNOTATION_CLASS);
            boolean methodAnnoFlag = method.isAnnotationPresent(PARAM_ANNOTATION_CLASS);
            if(parameters.length <= 0){
                return joinPoint.proceed();
            }
            for(int x = 0 ; x < args.length ; x++){
                Object arg = args[x];
                Parameter parameter = parameters[x];
                boolean paramAnnoFlag = parameter.isAnnotationPresent(PARAM_ANNOTATION_CLASS);
                if(paramAnnoFlag){
                    ParamAnnotation paramAnno = parameter.getAnnotation(PARAM_ANNOTATION_CLASS);
                    verify(arg,paramAnno);
                }
                if(methodAnnoFlag && ListUtil.isEmpty(errorMsg)){
                    verify(arg,methodAnno);
                }
            }
            if(!ListUtil.isEmpty(errorMsg)){
                String msg = ListUtil.toStr(errorMsg,"\n");
                if(RETURN_TYPE_NAME.equals(returnTypeName)){
                    return Resp.error(msg);
                }else{
                    throw new BusinessException(msg);
                }
            }
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            if(throwable instanceof BusinessException){
                throw new BusinessException(throwable.getMessage());
            }else{
                throw throwable;
            }
        }
    }

    /**
     * 参数校验
     * @param arg 参数
     * @param annotation 参数注解
     */
    private void verify(Object arg ,ParamAnnotation annotation ){
        String className = arg.getClass().getName();
        boolean b = arg instanceof Map;
        if(MAP_TYPE_NAME.equals(className) || b){
            handlerMap(arg,annotation);
        }else{
            handlerObj(arg);
        }
    }

    /**
     * 处理map
     * @param arg 参数
     * @param annotation 参数注解
     */
    private void handlerMap(Object arg ,ParamAnnotation annotation ){
        Map param = (Map) arg;
        ParamField[] fields = annotation.fields();
        for(ParamField field:fields){
            String name = field.name();
            String message = field.message();
            boolean isMsg = !StringUtil.isEmpty(message);
            String[] regular = field.regular();
            Class type = field.type();
            String fieldTypeName = type.getName();
            Object value = param.get(name);
            boolean nullAble = field.nullAble();
            if(!nullAble){
                checkNull(name,value,message);
                continue;
            }
            String paramTypeName = value.getClass().getName();
            if(!paramTypeName.equals(fieldTypeName)){
                if(isMsg){
                    errorMsg.add(message);
                }else{
                    errorMsg.add("参数【" + name + "】类型错误，允许参数类型为：【" + fieldTypeName +"】");
                }
                continue;
            }
            if(!ArrayUtil.isEmpty(regular)){
                checkRegular(name,value,message,regular);
                continue;
            }
        }
    }

    /**
     * 处理实体类
     * @param arg
     */
    private void handlerObj(Object arg){
        List<String> verify = NotNullVerifyUtil.verify(arg);
        errorMsg.addAll(verify);
    }

    /**
     *  校验是否为空
     * @param name  参数名
     * @param value 参数值
     * @param message   消息
     */
    private void checkNull(String name,Object value,String message){
        boolean isMsg = !StringUtil.isEmpty(message);
        if(value == null || "".equals(value)){
            if(isMsg){
                errorMsg.add(message);
            }else{
                errorMsg.add("参数【" + name + "】不允许为空");
            }
        }
    }

    /**
     * 校验正则
     * @param name 参数名
     * @param value 参数值
     * @param message 消息
     * @param regular 正则数组
     */
    private void checkRegular(String name,Object value,String message,String[] regular){
        boolean isMsg = !StringUtil.isEmpty(message);
        boolean flag = false;
        for(String r:regular){
            boolean matches = Pattern.matches(r, value.toString());
            if(matches){
                flag = true;
            }
        }
        if(!flag){
            if(isMsg){
                errorMsg.add(message);
            }else{
                errorMsg.add("参数【" + name + "】不是合法参数");
            }
        }
    }
}
