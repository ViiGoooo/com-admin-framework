package com.admin.framework.wechat.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.net.URLEncoder;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:28 2019\8\15 0015
 */
@JacksonXmlRootElement(localName = "xml")
@Data
public class WxMessageBase {

    /**
     * 	是	接收方帐号（收到的OpenID）
     */
    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;
    /**
     * 	是	开发者微信号
     */
    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;
    /**
     * 	是	消息创建时间 （整型）
     */
    @JacksonXmlProperty(localName = "CreateTime")
    private String createTime = String.valueOf(System.currentTimeMillis());

    /**
     * 	是	消息类型，文本为text
     */
    @JacksonXmlProperty(localName = "MsgType")
    private String msgType;


    public static void main(String[] args) {
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        String redirect = "http://testweixin.51vip.biz/mp/api/user/login";
        String encode = URLEncoder.encode(redirect);
        String link = String.format(url, "wx19e86539dded8a42", encode);
        System.out.println(link);
    }

}
