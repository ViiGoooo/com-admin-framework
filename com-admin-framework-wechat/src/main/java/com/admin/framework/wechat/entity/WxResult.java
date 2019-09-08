package com.admin.framework.wechat.entity;

import lombok.Data;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:47 2019\8\8 0008
 */
@Data
public class WxResult {
    /**
     * 错误码
     */
    private String errcode;

    /**
     * 错误消息
     */
    private String errmsg;

}
