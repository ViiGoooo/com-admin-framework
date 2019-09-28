package com.admin.framework.wechat.service.impl;

import com.admin.framework.component.http.HttpClient;
import com.admin.framework.component.http.HttpException;
import com.admin.framework.component.http.HttpResponse;
import com.admin.framework.component.utils.JSONUtil;
import com.admin.framework.component.utils.ListUtil;
import com.admin.framework.component.utils.NotNullVerifyUtil;
import com.admin.framework.component.utils.StringUtil;
import com.admin.framework.wechat.entity.WxConfig;
import com.admin.framework.wechat.entity.WxToken;
import com.admin.framework.wechat.exception.WxException;
import com.admin.framework.wechat.service.WxAuthService;

import java.util.List;

/**
 * @Author zsw
 * @Description
 * @Date Create in 10:18 2019\8\9 0009
 */
public class WxAuthServiceImpl implements WxAuthService {

    @Override
    public WxToken getAppToken(WxConfig config) throws WxException {
        List<String> verify = NotNullVerifyUtil.verify(config);
        if(ListUtil.isNotEmpty(verify)){
            throw new WxException(verify);
        }
        String url = String.format(appTokenUrl,config.getAppId(),config.getAppSecret());
        try {
            return getToken(url);
        } catch (HttpException e) {
            e.printStackTrace();
            throw new WxException(e);
        }
    }

    @Override
    public WxToken getWebToken(WxConfig wxConfig,String code) throws WxException{
        String url = String.format(webTokenUrl, wxConfig.getAppId(), wxConfig.getAppSecret(), code);
        try {
            return getToken(url);
        } catch (HttpException e) {
            e.printStackTrace();
            throw new WxException(e);
        }
    }

    @Override
    public WxToken refreshWebToken(String appId, String refreshToken) throws WxException{
        String url = String.format(webRefreshTokenUrl, appId, refreshToken);
        try {
            return getToken(url);
        } catch (HttpException e) {
            e.printStackTrace();
            throw new WxException(e);
        }
    }

    @Override
    public WxToken getJsApiTicket(String accessToken) throws WxException {
        String url = String.format(jsApiTicketUrl, accessToken);
        try {
            return getToken(url);
        } catch (HttpException e) {
            e.printStackTrace();
            throw new WxException(e);
        }
    }


    /**
     * 获取token
     * @param url
     * @return
     * @throws HttpException
     * @throws WxException
     */
    private WxToken getToken(String url) throws HttpException, WxException {
        HttpClient client = new HttpClient();
        HttpResponse request = client.post(url);
        String body = request.getBody();
        WxToken wxToken = JSONUtil.jsonToObj(body, WxToken.class);
        if(wxToken == null){
            throw new WxException("获取微信token发生异常");
        }
        if(!StringUtil.isEmpty(wxToken.getErrcode()) && !"0".equals(wxToken.getErrcode())){
            throw new WxException(wxToken.getErrmsg());
        }
        wxToken.setLoadTime(System.currentTimeMillis());
        return wxToken;
    }

}
