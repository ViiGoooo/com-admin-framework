package com.admin.framework.wechat.service.impl;

import com.admin.framework.component.utils.XMLUtil;
import com.admin.framework.wechat.contanst.WxMessageEventEnum;
import com.admin.framework.wechat.contanst.WxMessageTypeEnum;
import com.admin.framework.wechat.entity.WxMessageArticlesSend;
import com.admin.framework.wechat.entity.WxMessageReceive;
import com.admin.framework.wechat.entity.WxMessageTextSend;
import com.admin.framework.wechat.exception.WxException;
import com.admin.framework.wechat.service.WxMessageService;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


/**
 * @Author zsw
 * @Description
 * @Date Create in 11:12 2019\8\15 0015
 */
public class WxMessageServiceImpl implements WxMessageService {

    @Override
    public String sendText(String content, String fromUser, String toUser) throws WxException {
        WxMessageTextSend text = new WxMessageTextSend();
        text.setContent(content);
        text.setMsgType(WxMessageTypeEnum.TEXT.getValue());
        text.setFromUserName(fromUser);
        text.setToUserName(toUser);
        try {
            return XMLUtil.beanToXml(text);
        } catch (JsonProcessingException e) {
            throw new WxException(e);
        }
    }

    @Override
    public String sendArticles(List<WxMessageArticlesSend.Item> items, String fromUser, String toUser) throws WxException {
        WxMessageArticlesSend send = new WxMessageArticlesSend();
        send.setFromUserName(fromUser);
        send.setToUserName(toUser);
        send.setMsgType(WxMessageTypeEnum.NEWS.getValue());
        send.setArticleCount(items.size());
        WxMessageArticlesSend.Articles articles = new WxMessageArticlesSend.Articles();
        articles.setItems(items);
        send.setArticles(articles);
        try {
            return XMLUtil.beanToXml(send);
        } catch (JsonProcessingException e) {
            throw new WxException(e);
        }
    }

    @Override
    public String sendImage() {
        return null;
    }

    @Override
    public WxMessageEventEnum getEventType(WxMessageReceive receive) {
        String event = receive.getEvent();
        WxMessageEventEnum eventEnum = WxMessageEventEnum.getByValue(event);

        String msgType = receive.getMsgType();
        WxMessageTypeEnum typeEnum = WxMessageTypeEnum.getByValue(msgType);

        switch (typeEnum){
            case EVENT:
                return eventEnum;
        }
        return null;
    }



}
