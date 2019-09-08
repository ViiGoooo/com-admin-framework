package com.admin.framework.wechat.service;

import com.admin.framework.wechat.entity.MiniCode;
import com.admin.framework.wechat.exception.WxException;

import java.awt.image.BufferedImage;

/**
 * @Author zsw
 * @Description
 * @Date Create in 10:08 2019\8\9 0009
 */
public interface WxCodeService {


    /**
     * 获取小程序码
     * @param miniCode
     * @return
     * @throws WxException
     */
    String mini_code_url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=%s";
    BufferedImage getminiqrQr(MiniCode miniCode) throws WxException;

}
