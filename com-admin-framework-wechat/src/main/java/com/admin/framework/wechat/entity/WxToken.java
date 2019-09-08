package com.admin.framework.wechat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:26 2019\8\8 0008
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WxToken extends WxResult {
    /**
     * token
     */
    private String accessToken;

    /**
     * 过期时间
     */
    private long expiresIn;

    /**
     * 获取token的时间
     */
    private long loadTime;

    /**
     * 网页授权使用的刷新token
     */
    private String refreshToken;

    /**
     * 微信用户的openid 针对微信公众号唯一
     */
    private String openid;

    /**
     * 用户授权的作用域
     */
    private String scope;

    /**
     * jsapi_ticket
     */
    private String ticket;

}
