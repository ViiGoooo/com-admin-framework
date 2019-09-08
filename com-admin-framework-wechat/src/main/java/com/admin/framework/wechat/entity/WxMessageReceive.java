package com.admin.framework.wechat.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;


/**
 * 接受到的微信消息
 * @Author zsw
 * @Description
 * @Date Create in 11:01 2019\8\15 0015
 */
@Data
public class WxMessageReceive extends WxMessageBase{

     String template = "<xml><ToUserName><![CDATA[gh_9b17753f3bd7]]></ToUserName>\n" +
             "     <FromUserName><![CDATA[o4z5lxMN13kH2ZsnPlWf8-7jnIys]]></FromUserName>\n" +
             "     <CreateTime>1565839770</CreateTime>\n" +
             "     <MsgType><![CDATA[event]]></MsgType>\n" +
             "     <Event><![CDATA[CLICK]]></Event>\n" +
             "     <EventKey><![CDATA[aaa]]></EventKey>\n" +
             "     </xml>";


    /**
     * 	事件类型，VIEW
     */
    @JacksonXmlProperty(localName = "Event")
    private String event;
    /**
     * 	事件KEY值，设置的跳转URL
     */
    @JacksonXmlProperty(localName = "EventKey")
    private String eventKey;
}
