package com.admin.framework.wechat.entity;

import lombok.Data;


/**
 * 微信统一下单接口返回参数
 * @Author zsw
 * @Description
 * @Date Create in 16:30 2019\8\14 0014
 */
@Data
public class WxUnifiedOrderResponse {
    /**
     * 返回状态码
     */
    private String returnCode;

    /**
     * 消息
     */
    private String returnMsg;

    /**
     * 调用接口提交的公众账号ID
     */
    private String appid;

    /**
     * 调用接口提交的商户号
     */
    private String mchId;

    /**
     * 微信返回的随机字符串
     */
    private String nonceStr;
    private String openid;

    /**
     * 微信返回的签名值，详见签名算法
     */
    private String sign;

    /**
     * 业务结果
     */
    private String resultCode;

    /**
     * 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    private String prepayId;

    /**
     * JSAPI -JSAPI支付
     *
     * NATIVE -Native支付
     *
     * APP -APP支付
     *
     * 说明详见参数规定
     */
    private String tradeType;

}
