package com.admin.framework.wechat.entity;

import lombok.Data;

/**
 * @Author zsw
 * @Description
 * @Date Create in 14:21 2019\9\17 0017
 */
@Data
public class WxMediaResponse extends WxResult {

    /**
     * 微信返回的mediaId
     */
    private String mediaId;

    /**
     * 微信返回的url
     */
    private String url;

}
