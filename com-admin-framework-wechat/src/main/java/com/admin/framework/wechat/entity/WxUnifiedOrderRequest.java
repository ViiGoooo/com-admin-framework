package com.admin.framework.wechat.entity;

import com.admin.framework.component.annotations.NotNull;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;


/**
 * 微信统一下单接口请求参数
 * @Author zsw
 * @Description
 * @Date Create in 16:30 2019\8\14 0014
 */
@Data
@JacksonXmlRootElement(localName = "xml")
public class WxUnifiedOrderRequest {

    /**
     * 微信支付分配的公众账号ID（企业号corpid即为此appId）
     */
    @NotNull(message = "appid不能为空")
    private String appid;

    /**
     * 微信支付分配的商户号
     */
    @NotNull(message = "商户id不能为空")
    private String mchId;

    /**
     * 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    private String deviceInfo;

    /**
     * 随机字符串，长度要求在32位以内。推荐随机数生成算法
     */
//    @NotNull(message = "随机字符串不能为空")
    private String nonceStr;

    /**
     * 通过签名算法计算得出的签名值，详见签名生成算法
     */
//    @NotNull(message = "签名不能为空")
    private String sign;

    /**
     * 签名类型，默认为MD5，支持HMAC-SHA256和MD5。
     */
    private String signType;

    /**
     * 商品简单描述，该字段请按照规范传递，具体请见参数规定
     */
    @NotNull(message = "商品描述不能为空")
    private String body;

    /**
     * 商品详细描述，对于使用单品优惠的商户，该字段必须按照规范上传，详见“单品优惠参数说明”
     */
    private String detail;

    /**
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     */
    private String attach;

    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一。详见商户订单号
     */
    @NotNull(message = "订单号不能为空")
    private String outTradeNo;

    /**
     * 符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
     */
    private String feeType;

    /**
     * 订单总金额，单位为分，详见支付金额
     */
    @NotNull(message = "金额不能为空")
    private Integer totalFee;

    /**
     * 支持IPV4和IPV6两种格式的IP地址。用户的客户端IP
     */
    @NotNull(message = "ip不能为空")
    private String spbillCreateIp;

    /**
     * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     */
    private String timeStart;

    /**
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。其他详见时间规则
     */
    private String timeExpire;

    /**
     * 订单优惠标记，使用代金券或立减优惠功能时需要的参数，说明详见代金券或立减优惠
     */
    private String goodsTag;

    /**
     * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    @NotNull(message = "异步通知地址不能为空")
    private String notifyUrl;

    /**
     * JSAPI -JSAPI支付
     *
     * NATIVE -Native支付
     *
     * APP -APP支付
     *
     * 说明详见参数规定
     */
    @NotNull(message = "支付类型不能为空")
    private String tradeType;

    /**
     * trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
     */
    private String productId;

    /**
     * 上传此参数no_credit--可限制用户不能使用信用卡支付
     */
    private String limitPay;

    /**
     * trade_type=JSAPI时（即JSAPI支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
     */
    private String openid;

    /**
     * Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
     */
    private String receipt;

    /**
     * 该字段常用于线下活动时的场景信息上报，支持上报实际门店信息，商户也可以按需求自己上报相关信息。该字段为JSON对象数据，对象格式为{"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }} ，字段详细说明请点击行前的+展开
     */
    private String sceneInfo;



}
