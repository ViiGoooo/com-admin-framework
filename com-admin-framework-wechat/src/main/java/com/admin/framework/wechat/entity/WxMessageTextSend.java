package com.admin.framework.wechat.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:08 2019\8\15 0015
 */
@Data
public class WxMessageTextSend extends WxMessageBase{

    /**
     * 消息内容
     */
    @JacksonXmlProperty(localName = "Content")
    private String content;


}
