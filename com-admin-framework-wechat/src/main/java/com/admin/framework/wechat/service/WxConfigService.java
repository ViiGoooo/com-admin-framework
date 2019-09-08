package com.admin.framework.wechat.service;

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

}
