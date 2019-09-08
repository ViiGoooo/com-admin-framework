package com.admin.framework.wechat.entity;

import com.admin.framework.component.utils.XMLUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 微信支付回调借口返回参数
 * @Author zsw
 * @Description
 * @Date Create in 18:44 2019\8\14 0014
 */
@JacksonXmlRootElement(localName = "xml")
public class WxPayNotifyReturn {
    private String returnCode = "SUCCESS";
    private String returnMsg = "OK";

    public String getReturnCode() {
        return returnCode;
    }
    public String getReturnMsg() {
        return returnMsg;
    }


    public static void main(String[] args) throws JsonProcessingException {
        String s = XMLUtil.beanToXml(new WxPayNotifyReturn());
        System.out.println(s);
    }

}
