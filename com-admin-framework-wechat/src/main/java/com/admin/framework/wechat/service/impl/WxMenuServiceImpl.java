package com.admin.framework.wechat.service.impl;

import com.admin.framework.component.http.HttpClient;
import com.admin.framework.component.http.HttpException;
import com.admin.framework.component.http.HttpResponse;
import com.admin.framework.component.utils.JSONUtil;
import com.admin.framework.component.utils.ListUtil;
import com.admin.framework.component.utils.NotNullVerifyUtil;
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
    public void createMenu(List<WxButton> wxButtons, String accessToken) throws WxException {

        for(WxButton e:wxButtons){
            List<String> verify = NotNullVerifyUtil.verify(e);
            if(!ListUtil.isEmpty(verify)){
                throw new WxException(verify);
            }
            convert(e);
            List<WxButton> subButton = e.getSubButton();
            convert(subButton);
        }

        String url = String.format(create_menu,accessToken);
        Map map = new HashMap<>();
        map.put("button",wxButtons);
        String param = JSONUtil.objToJsonStr(map);
        HttpClient httpClient = new HttpClient();
        try {
            HttpResponse response = httpClient.post(url, param);
            String body = response.getBody();
            WxResult wxResult = JSONUtil.jsonToObj(body, WxResult.class);
            if(!"ok".equals(wxResult.getErrmsg())){
                throw new WxException(wxResult.getErrmsg());
            }
        } catch (HttpException e) {
            throw new WxException(e);
        }
    }

    @Override
    public void remove(String accessToken) throws WxException {
        HttpClient client = new HttpClient();
        try {
            String url = String.format(remove_menu,accessToken);
            HttpResponse response = client.post(url);
            String body = response.getBody();
            WxResult wxResult = JSONUtil.jsonToObj(body, WxResult.class);
            if(!"ok".equals(wxResult.getErrmsg())){
                throw new WxException(wxResult.getErrmsg());
            }
        } catch (HttpException e) {
            throw new WxException(e);
        }
    }

    /**
     * 转换数据
     * @param wxButtons
     */
    private void convert(List<WxButton> wxButtons){
        if(ListUtil.isEmpty(wxButtons)){
            return;
        }
        wxButtons.forEach(e->{
            WxButtonTypeEnum typeEnum = e.getTypeEnum();
            e.setType(typeEnum.getValue());
        });

    }

    /**
     * 转换数据
     * @param wxButton
     */
    private void convert(WxButton wxButton){
        WxButtonTypeEnum typeEnum = wxButton.getTypeEnum();
        wxButton.setType(typeEnum.getValue());
        wxButton.setTypeEnum(null);
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
        button3.setName("qq");
        button3.setType(WxButtonTypeEnum.MINI_PROGRAM.getValue());
        button3.setAppid("wxa2af042698930ccb");
        button3.setPagepath("pages/index/index");
        button3.setUrl("http://chengmall.cn");

//        WxAuthService wxAuthService = new WxAuthServiceImpl();
//        WxToken wxToken = wxAuthService.getAppToken(new WxConfig("wx53b03c84d9aa1e79", "8f34b7737f154a7785e664340f644c12"));

        String token = "25_3QjDD_HXUGlghzwlNqBSPtmgsirPtmb9zE6o8sk4CB7NDLw1MIcZt6RAW7BZNYhzXLp8DhTiASmxoUEuO8ZE9U2Kn1PTE8Pl7tyoDIy9bfGDASIxM2jsxB9m7vUcr9w7C6PwBCPAk5gD4c0DQPOhAEAIHG";
        List<WxButton> wxButtons = new ArrayList<>();
        wxButtons.add(button1);
        wxButtons.add(button2);
        WxMenuService wxMenuService = new WxMenuServiceImpl();
        wxMenuService.createMenu(wxButtons,token);
    }

}
