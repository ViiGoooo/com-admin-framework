package com.admin.framework.wechat.service;

import com.admin.framework.wechat.entity.WxJsConfig;
import com.admin.framework.wechat.exception.WxException;

/**
 * @Author zsw
 * @Description
 * @Date Create in 16:18 2019\8\23 0023
 */
public interface WxConfigService {
    /**
     * 签名校验
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    boolean checkSignature(String token,String signature, String timestamp, String nonce);



    /**
     * 获取jsapi的初始化值
     * @param ticket
     * @param url
     * @return
     */
    WxJsConfig jsConfig(String ticket, String url) throws WxException;


}
