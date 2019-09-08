package com.admin.framework.wechat.service;

import com.admin.framework.wechat.contanst.WxMessageEventEnum;
import com.admin.framework.wechat.contanst.WxMessageTypeEnum;
import com.admin.framework.wechat.entity.WxMessageArticlesSend;
import com.admin.framework.wechat.entity.WxMessageReceive;
import com.admin.framework.wechat.exception.WxException;

import java.util.List;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:01 2019\8\15 0015
 */
public interface WxMessageService {

    /**
     * 发送文本消息
     * @param content   消息内容
     * @param fromUser  开发者微信号
     * @param toUser    接收方openid
     * @return
     * @throws WxException
     */
    String sendText(String content,String fromUser,String toUser) throws WxException;

    /**
     * 发送图文消息
     * @param items
     * @param fromUser
     * @param toUser
     * @return
     * @throws WxException
     */
    String sendArticles(List<WxMessageArticlesSend.Item> items, String fromUser, String toUser) throws WxException;


    String sendImage();

    /**
     * 是否为菜单点击事件
     * @param receive
     * @return
     */
    WxMessageEventEnum getEventType(WxMessageReceive receive);



}
