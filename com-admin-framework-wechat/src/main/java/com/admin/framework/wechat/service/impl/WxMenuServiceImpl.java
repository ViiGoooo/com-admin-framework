package com.admin.framework.wechat.service.impl;

import com.admin.framework.component.http.HttpClient;
import com.admin.framework.component.http.HttpException;
import com.admin.framework.component.http.HttpResponse;
import com.admin.framework.component.utils.JSONUtil;
import com.admin.framework.wechat.contanst.WxButtonTypeEnum;
import com.admin.framework.wechat.entity.WxButton;
import com.admin.framework.wechat.entity.WxConfig;
import com.admin.framework.wechat.entity.WxResult;
import com.admin.framework.wechat.entity.WxToken;
import com.admin.framework.wechat.exception.WxException;
import com.admin.framework.wechat.service.WxAuthService;
import com.admin.framework.wechat.service.WxMenuService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zsw
 * @Description
 * @Date Create in 9:56 2019\8\15 0015
 */
public class WxMenuServiceImpl implements WxMenuService {
    @Override
    public void createMenu(List<WxButton> wxButton, String accessToken) throws WxException {
        String url = String.format(create_menu,accessToken);
        Map map = new HashMap<>();
        map.put("button",wxButton);
        String param = JSONUtil.objToJsonStr(map);
        HttpClient httpClient = new HttpClient();
        try {
            HttpResponse response = httpClient.request(url, param);
            String body = response.getBody();
            WxResult wxResult = JSONUtil.jsonToObj(body, WxResult.class);
            if(!"ok".equals(wxResult.getErrmsg())){
                throw new WxException(wxResult.getErrmsg());
            }
        } catch (HttpException e) {
            throw new WxException(e);
        }
    }


    public static void main(String[] args) throws WxException {
        WxButton button1 = new WxButton();
        button1.setName("走进成氏");
        button1.setType(WxButtonTypeEnum.VIEW.getValue());
        button1.setUrl("http://m.chengmall.cn");


        WxButton button2 = new WxButton();
        button2.setName("成氏学院");
        button2.setType(WxButtonTypeEnum.CLICK.getValue());
        button2.setKey("推动成氏经济发展，成氏企业家论坛，连接成氏企业资源，成氏创业辅助。");


        WxButton button3 = new WxButton();
        button3.setName("");
        button3.setType(WxButtonTypeEnum.MINI_PROGRAM.getValue());
        button3.setAppid("wxa2af042698930ccb");
        button3.setPagepath("pages/index/index");
        button3.setUrl("http://chengmall.cn");

//        WxAuthService wxAuthService = new WxAuthServiceImpl();
//        WxToken wxToken = wxAuthService.getAppToken(new WxConfig("wx53b03c84d9aa1e79", "8f34b7737f154a7785e664340f644c12"));

        String token = "24_yt6utLEjI0tM3shthNTdy7KJOqQAN-ePfeOBRdnVROBTbZzh-odM7LxPH9o0gxMce7ybQ_1WB_xzeJ_7NM51eJM2AGbLVZvLgvWBFBVQ9CgL-hbhCWXSn046UTHUfFip5nDg0JFl_by-TE9-PHEcAAASKH";
        List<WxButton> wxButtons = new ArrayList<>();
        wxButtons.add(button1);
        wxButtons.add(button2);
        WxMenuService wxMenuService = new WxMenuServiceImpl();
        wxMenuService.createMenu(wxButtons,token);
    }

}
