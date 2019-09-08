package com.admin.framework.wechat.entity;

import lombok.Data;

/**
 * @Author zsw
 * @Description
 * @Date Create in 16:43 2019\8\15 0015
 */
@Data
public class WxJsConfig {
    /**
     *  必填，生成签名的时间戳
     */
    private String timestamp;
    /**
     *  必填，生成签名的随机串
     */
    private String nonceStr;
    /**
     *  必填，签名，见附录1
     */
    private String signature;
    /**
     *  必填，需要使用的JS接口列表，所有JS接口列表见附录2
     */
    private String jsApiList;

}
