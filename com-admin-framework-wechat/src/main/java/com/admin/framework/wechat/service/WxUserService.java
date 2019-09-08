package com.admin.framework.wechat.service;

import com.admin.framework.wechat.entity.WxUser;
import com.admin.framework.wechat.exception.WxException;
import com.admin.framework.wechat.service.impl.WxUserServiceImpl;

/**
 * @Author zsw
 * @Description
 * @Date Create in 19:54 2019\8\8 0008
 */
public interface WxUserService {

    /**
     * 获取用户基本信息
     * @param accessToken     公众号的appid
     * @param openId    用户的openid
     * @return
     * @throws Exception
     */
    String appInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
    WxUser appInfo(String accessToken,String openId) throws WxException;



    /**
     * 网页授权获取用户基本信息
     * @param accessToken
     * @param openId
     * @return
     * @throws WxException
     */
    String webInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
    WxUser webInfo(String accessToken, String openId) throws WxException;



}
