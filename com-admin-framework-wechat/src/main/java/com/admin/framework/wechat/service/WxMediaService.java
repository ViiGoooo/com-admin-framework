package com.admin.framework.wechat.service;


import com.admin.framework.wechat.entity.WxMediaRequest;
import com.admin.framework.wechat.entity.WxMediaResponse;
import com.admin.framework.wechat.exception.WxException;

/**
 * @Author zsw
 * @Description
 * @Date Create in 21:10 2019\9\15 0015
 */
public interface WxMediaService {

    /**
     * 创建永久素材
     * @param mediaRequest
     */
    String cratePermanentUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s";
    WxMediaResponse createPermanent(WxMediaRequest mediaRequest) throws WxException;

    /**
     * 删除永久素材
     * @param mediaId
     */
    String deletePermanentUrl = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=%s";
    void deletePermanent(String mediaId,String accessToken) throws WxException;



}
