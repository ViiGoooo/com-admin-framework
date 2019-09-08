package com.admin.framework.wechat.entity;

import com.admin.framework.component.annotations.NotNull;
import lombok.Data;

/**
 * @Author zsw
 * @Description
 * @Date Create in 14:32 2019\8\8 0008
 */
@Data
public class WxConfig {

    @NotNull(message = "appId不能为空")
    private String appId;
    @NotNull(message = "appSecret不能为空")
    private String appSecret;

    public WxConfig(){}

    public WxConfig(String appId,String appSecret){
        this.appId = appId;
        this.appSecret = appSecret;
    }

}
