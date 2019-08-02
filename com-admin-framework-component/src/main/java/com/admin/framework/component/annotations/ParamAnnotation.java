package com.admin.framework.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ZSW on 2018/11/21.
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamAnnotation {

    /**
     * 参数校验
     * @return
     */
    ParamField[] fields() default {};

}
