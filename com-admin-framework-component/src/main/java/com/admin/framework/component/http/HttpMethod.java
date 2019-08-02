package com.admin.framework.component.http;

import org.springframework.web.util.pattern.PathPattern;

/**
 * Created by ZSW on 2019/2/25.
 */
public enum HttpMethod {

    POST("POST"),
    GET("GET");

    HttpMethod(String method){
        this.method = method;
    }

    private String method;

}
