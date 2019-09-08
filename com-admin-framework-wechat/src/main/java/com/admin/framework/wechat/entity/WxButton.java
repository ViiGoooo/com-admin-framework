package com.admin.framework.wechat.entity;

import com.admin.framework.wechat.contanst.WxButtonTypeEnum;
import com.admin.framework.wechat.exception.WxException;
import com.admin.framework.wechat.service.WxAuthService;
import com.admin.framework.wechat.service.WxMenuService;
import com.admin.framework.wechat.service.impl.WxAuthServiceImpl;
import com.admin.framework.wechat.service.impl.WxMenuServiceImpl;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信菜单
 * @Author zsw
 * @Description
 * @Date Create in 9:57 2019\8\15 0015
 */
@Data
public class WxButton {
    private String key;
    private String type;
    private String name;
    private String url;
    private String appid;
    private String pagepath;
    private List<WxButton> subButton;


    public static void main(String[] args) throws WxException {
        List<WxButton> buttons = new ArrayList<>();
        WxButton button1 = new WxButton();
        button1.setUrl("http://m.chengmall.cn/");
        button1.setType(WxButtonTypeEnum.VIEW.getValue());
        button1.setName("走进成氏");
        buttons.add(button1);

        WxButton button2 = new WxButton();
        button2.setUrl("http://dev.chengmall.cn/");
        button2.setType(WxButtonTypeEnum.VIEW.getValue());
        button2.setName("成氏学院");
        buttons.add(button2);

        WxButton button3 = new WxButton();
        button3.setName("成氏商城");
        button3.setType(WxButtonTypeEnum.MINI_PROGRAM.getValue());
        button3.setUrl("http://dev.chengmall.cn/");
        button3.setAppid("wxa2af042698930ccb");
        button3.setPagepath("pages/index/index");
        buttons.add(button3);

        WxAuthService authService = new WxAuthServiceImpl();
        WxToken appToken = authService.getAppToken(new WxConfig("wx19e86539dded8a42", "d02c5ba9da815ba23ed1fe34989be6ca"));
        String accessToken = appToken.getAccessToken();
        WxMenuService wxMenuService = new WxMenuServiceImpl();
        wxMenuService.createMenu(buttons,accessToken);



    }

}
