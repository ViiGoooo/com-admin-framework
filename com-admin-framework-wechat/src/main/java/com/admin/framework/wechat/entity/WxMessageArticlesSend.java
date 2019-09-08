package com.admin.framework.wechat.entity;

import com.admin.framework.component.utils.XMLUtil;
import com.admin.framework.wechat.contanst.WxMessageTypeEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zsw
 * @Description
 * @Date Create in 19:00 2019\8\21 0021
 */
@Data
public class WxMessageArticlesSend extends WxMessageBase {

/*
 <xml>
 <ToUserName><![CDATA[toUser]]></ToUserName>
 <FromUserName><![CDATA[fromUser]]></FromUserName>
 <CreateTime>12345678</CreateTime>
 <MsgType><![CDATA[news]]></MsgType>
 <ArticleCount>1</ArticleCount>
 <Articles>
 <item>
 <Title><![CDATA[title1]]></Title>
 <Description><![CDATA[description1]]></Description>
 <PicUrl><![CDATA[picurl]]></PicUrl>
 <Url><![CDATA[url]]></Url>
 </item>
 </Articles>
 </xml>

 */
    @JacksonXmlProperty(localName = "ArticleCount")
    private Integer articleCount;

    @JacksonXmlProperty(localName = "Articles")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Articles articles;

    @Data
    public static class Articles{
        @JacksonXmlProperty(localName = "item")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<Item> items;
    }

    @Data
    public static class Item{
        @JacksonXmlProperty(localName = "Title")
        private String title;
        @JacksonXmlProperty(localName = "Description")
        private String description;
        @JacksonXmlProperty(localName = "PicUrl")
        private String picUrl;
        @JacksonXmlProperty(localName = "Url")
        private String url;
    }

}
