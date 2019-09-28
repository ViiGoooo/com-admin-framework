package com.admin.framework.wechat.entity;

import com.admin.framework.component.annotations.NotNull;
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
    @NotNull
    private WxButtonTypeEnum typeEnum;

    @NotNull
    private String name;
    private String url;
    private String appid;
    private String pagepath;
    private List<WxButton> subButton;


}
