package com.admin.framework.wechat.service.impl;

import com.admin.framework.component.http.HttpClient;
import com.admin.framework.component.http.HttpException;
import com.admin.framework.component.http.HttpResponse;
import com.admin.framework.component.utils.JSONUtil;
import com.admin.framework.component.utils.ListUtil;
import com.admin.framework.component.utils.NotNullVerifyUtil;
import com.admin.framework.component.utils.StringUtil;
import com.admin.framework.wechat.contanst.WxMediaTypeEnum;
import com.admin.framework.wechat.entity.WxMediaRequest;
import com.admin.framework.wechat.entity.WxMediaResponse;
import com.admin.framework.wechat.entity.WxResult;
import com.admin.framework.wechat.exception.WxException;
import com.admin.framework.wechat.service.WxMediaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zsw
 * @Description
 * @Date Create in 14:11 2019\9\17 0017
 */
public class WxMediaServiceImpl implements WxMediaService {
    @Override
    public WxMediaResponse createPermanent(WxMediaRequest mediaRequest) throws WxException {
        List<String> verify = NotNullVerifyUtil.verify(mediaRequest);
        if(!ListUtil.isEmpty(verify)){
            throw new WxException(verify);
        }
        WxMediaTypeEnum type = mediaRequest.getType();
        String url = String.format(cratePermanentUrl,mediaRequest.getAccessToken(),type.getValue());
        HttpClient client = new HttpClient();
        try {
            HttpResponse response = client.postForm(url, mediaRequest.getInputStream(), mediaRequest.getFileName());
            String body = response.getBody();
            WxMediaResponse wxMediaResponse = JSONUtil.jsonToObj(body, WxMediaResponse.class);
            String errcode = wxMediaResponse.getErrcode();
            if(!StringUtil.isEmpty(errcode) && !"0".equals(errcode)){
                throw new WxException(body);
            }
            return wxMediaResponse;
        } catch (HttpException e) {
            e.printStackTrace();
            throw new WxException(e);
        }
    }

    @Override
    public void deletePermanent(String mediaId,String accessToken) throws WxException {
        String url = String.format(deletePermanentUrl, accessToken);

        Map param = new HashMap();
        param.put("media_id",mediaId);

        HttpClient httpClient = new HttpClient();
        try {
            HttpResponse post = httpClient.post(url, JSONUtil.objToJsonStr(param));
            String body = post.getBody();
            WxResult wxResult = JSONUtil.jsonToObj(body, WxResult.class);
            String errcode = wxResult.getErrcode();
            if(!StringUtil.isEmpty(errcode) && !"0".equals(errcode)){
                throw new WxException(body);
            }
        } catch (HttpException e) {
            e.printStackTrace();
            throw new WxException(e);
        }

    }
}
