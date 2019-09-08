package com.admin.framework.wechat.service.impl;

import com.admin.framework.component.http.HttpClient;
import com.admin.framework.component.http.HttpException;
import com.admin.framework.component.http.HttpResponse;
import com.admin.framework.component.utils.JSONUtil;
import com.admin.framework.component.utils.StringUtil;
import com.admin.framework.wechat.entity.WxConfig;
import com.admin.framework.wechat.entity.WxResult;
import com.admin.framework.wechat.entity.WxToken;
import com.admin.framework.wechat.entity.WxUser;
import com.admin.framework.wechat.exception.WxException;
import com.admin.framework.wechat.service.WxAuthService;
import com.admin.framework.wechat.service.WxUserService;

/**
 * @Author zsw
 * @Description
 * @Date Create in 10:04 2019\8\9 0009
 */
public class WxUserServiceImpl implements WxUserService {

    /**
     * 获取用户基本信息
     * @return
     */
    public WxUser appInfo(String accessToken,String openId) throws WxException{
        String url = String.format(appInfoUrl,accessToken,openId);
        try {
            return getUser(url);
        } catch (HttpException e) {
            e.printStackTrace();
            throw new WxException(e);
        }
    }

    @Override
    public WxUser webInfo(String accessToken, String openId) throws WxException {
        String url = String.format(webInfoUrl, accessToken, openId);
        try {
            return getUser(url);
        } catch (HttpException e) {
            e.printStackTrace();
            throw new WxException(e);
        }
    }



    private WxUser getUser(String url) throws HttpException, WxException {
        HttpClient httpClient = new HttpClient();
        HttpResponse response = httpClient.request(url);
        int i = response.getCode();
        if(i != 200){
            throw new WxException("获取微信用户信息失败");
        }
        String body = response.getBody();
        WxResult wxResult = JSONUtil.jsonToObj(body, WxResult.class);
        if(!StringUtil.isEmpty(wxResult.getErrcode()) && !"0".equals(wxResult.getErrcode())){
            throw new WxException(wxResult.getErrmsg());
        }
        return WxUser.build(body);
    }

    public static void main(String[] args) throws WxException {

        WxAuthService wxAuthService = new WxAuthServiceImpl();
        WxToken token = wxAuthService.getAppToken(new WxConfig("wxe07952d459662408", "54bf6347d052c567e35469c3371f9f55"));

        WxUserService wxUserService = new WxUserServiceImpl();
        WxUser wxUser = wxUserService.appInfo(token.getAccessToken(), "oCnxJ6Mw_u37YVjn5vBj_JblakOA");
        System.out.println(JSONUtil.objToJsonStr(wxUser));



    }


}
