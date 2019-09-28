package com.admin.framework.wechat.service;

import com.admin.framework.wechat.entity.*;
import com.admin.framework.wechat.exception.WxException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zsw
 * @Description
 * @Date Create in 16:28 2019\8\14 0014
 */
public interface WxPayService {

    /**
     * 微信统一下单
     * @param request
     * @return
     */
    String unified_order = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    WxUnifiedOrderResult unifiedOrder(WxUnifiedOrderRequest request,String key) throws WxException;

    /**
     * 获取微信回调返回的数据
     * @param request
     * @return
     */
    WxPayNotifyResponse notifyResponse(HttpServletRequest request) throws WxException;

    /**
     * 响应给微信的数据
     * @return
     * @throws WxException
     */
    String notifyReturn() throws WxException;

}
