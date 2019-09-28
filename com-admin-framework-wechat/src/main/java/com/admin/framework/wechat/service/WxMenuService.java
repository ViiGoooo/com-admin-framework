package com.admin.framework.wechat.service;

import com.admin.framework.wechat.entity.WxButton;
import com.admin.framework.wechat.exception.WxException;

import java.util.List;

/**
 * 微信菜单处理
 * @Author zsw
 * @Description
 * @Date Create in 9:56 2019\8\15 0015
 */
public interface WxMenuService {


    /**
     * 创建微信自定义菜单
     * @param wxButton
     */
    String create_menu = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
    void createMenu(List<WxButton> wxButton, String accessToken) throws WxException;

    /**
     * 删除
     * @throws WxException
     */
    String remove_menu = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";
    void remove(String accessToken) throws WxException;


}
