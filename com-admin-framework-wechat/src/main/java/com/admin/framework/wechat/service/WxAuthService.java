package com.admin.framework.wechat.service;

import com.admin.framework.wechat.entity.WxConfig;
import com.admin.framework.wechat.entity.WxToken;
import com.admin.framework.wechat.exception.WxException;

/**
 * @Author zsw
 * @Description
 * @Date Create in 10:18 2019\8\9 0009
 */
public interface WxAuthService {

    /**
     * 获取普通调用的token
     * @param config
     * @return
     * @throws WxException
     */
    String appTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    WxToken getAppToken(WxConfig config) throws WxException;


    /**
     * 获取网页授权的token
     * @param wxConfig
     * @return
     */
    String webTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    WxToken getWebToken(WxConfig wxConfig,String code) throws WxException;


    /**
     * 网页授权token刷新
     * @param appId
     * @param refreshToken
     * @return
     */
    String webRefreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
    WxToken refreshWebToken(String appId,String refreshToken) throws WxException;


    /**
     * 获取jsApiTicket
     * @param accessToken
     * @return
     */
    String jsApiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
    WxToken getJsApiTicket(String accessToken) throws WxException;

}
