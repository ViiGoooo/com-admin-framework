package com.admin.framework.component.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by ZSW on 2018/12/20.
 */
@Target(ElementType.METHOD)
public @interface ParamField {

    String name();
    Class type();

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
     * 是否可以为空
     * true-可以
     * false-不可以
     * @return
     */
    boolean nullAble() default false;

}
