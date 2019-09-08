package com.admin.framework.wechat.entity;

import lombok.Data;

/**
 * @Author zsw
 * @Description
 * @Date Create in 18:51 2019\8\15 0015
 */
@Data
public class WxUnifiedOrderResult {


    private String timeStamp;
    private String nonceStr;
    private String pg;
    private String signType;
    private String paySign;

}
