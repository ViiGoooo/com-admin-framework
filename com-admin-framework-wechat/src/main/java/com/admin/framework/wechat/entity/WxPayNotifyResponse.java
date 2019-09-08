package com.admin.framework.wechat.entity;

import lombok.Data;

/**
 * @Author zsw
 * @Description
 * @Date Create in 18:58 2019\8\14 0014
 */
@Data
public class WxPayNotifyResponse {

    /**
     * 返回状态码
     */
    private String returnCode;
    /**
     * 返回信息
     */
    private String returnMsg;
    /**
     * 微信分配的公众账号ID（企业号corpid即为此appId）
     */
    private String appid;
    /**
     * 微信支付分配的商户号
     */
    private String mchId;
    /**
     * 微信支付分配的终端设备号，
     */
    private String deviceInfo;
    /**
     * 随机字符串，不长于32位
     */
    private String nonceStr;
    /**
     * 签名，详见签名算法
     */
    private String sign;
    /**
     * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     */
    private String signType;
    /**
     * 业务结果
     */
    private String resultCode;
    /**
     * 错误返回的信息描述
     */
    private String errCode;
    /**
     * 错误返回的信息描述
     */
    private String errCodeDes;
    /**
     * 用户在商户appid下的唯一标识
     */
    private String openid;
    /**
     * 用户是否关注公众账号，Y-关注，N-未关注
     */
    private String isSubscribe;
    /**
     * JSAPI、NATIVE、APP
     */
    private String tradeType;
    /**
     * 银行类型，采用字符串类型的银行标识，银行类型见银行列表
     */
    private String bankType;
    /**
     * 订单总金额，单位为分
     */
    private String totalFee;
    /**
     * 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    private String settlementTotalFee;
    /**
     * 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String feeType;
    /**
     * 现金支付金额订单现金支付金额，详见支付金额
     */
    private String cashFee;
    /**
     * 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String cashFeeType;
    /**
     * 代金券金额<=订单金额，订单金额-代金券金额=现金支付金额，详见支付金额
     */
    private String couponFee;
    /**
     * 代金券使用数量
     */
    private String couponCount;
    /**
     * 微信支付订单号
     */
    private String transactionId;
    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    private String outTradeNo;
    /**
     * 商家数据包，原样返回
     */
    private String attach;
    /**
     * 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     */
    private String timeEnd;

}
