package com.admin.framework.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ZSW on 2019/1/17.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {

    /**
     * 正则
     * @return
     */
    String[] regular() default {};

    /**
     * 响应消息
     * @return
     */
    String message() default "";

    /**
     * 最大长度
     * @return
     */
    int maxLength() default 0;

    /**
     * 最小长度
     * @return
     */
    int minLength() default 0;

    /**
     * 是否可以为空
     * true-可以
     * false-不可以
     * @return
     */
    boolean nullAble() default false;




}
