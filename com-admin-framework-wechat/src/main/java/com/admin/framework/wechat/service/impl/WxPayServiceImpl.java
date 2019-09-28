package com.admin.framework.wechat.service.impl;

import com.admin.framework.component.http.HttpClient;
import com.admin.framework.component.http.HttpException;
import com.admin.framework.component.http.HttpResponse;
import com.admin.framework.component.utils.*;
import com.admin.framework.wechat.contanst.WxPayReturnCodeEnum;
import com.admin.framework.wechat.contanst.WxPayTradeTypeEnum;
import com.admin.framework.wechat.entity.*;
import com.admin.framework.wechat.exception.WxException;
import com.admin.framework.wechat.service.WxPayService;
import com.admin.framework.wechat.utils.WxPayUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Author zsw
 * @Description
 * @Date Create in 17:32 2019\8\14 0014
 */
public class WxPayServiceImpl implements WxPayService {
    @Override
    public WxUnifiedOrderResult unifiedOrder(WxUnifiedOrderRequest request,String key) throws WxException {
        List<String> verify = NotNullVerifyUtil.verify(request);
        if(!ListUtil.isEmpty(verify)){
            throw new WxException(verify.get(0));
        }
        if(WxPayTradeTypeEnum.JSAPI.getValue().equals(request.getTradeType()) && StringUtil.isEmpty(request.getOpenid())){
            throw new WxException("jsapi支付必须传openid");
        }
        try {
            //提交前签名
            String nonceStr = RandomUtil.getAllRandomStr(32);
            request.setNonceStr(nonceStr);
            ReflectUil.hump_to_underline = true;
            Map<String, Object> map = ReflectUil.beanToMap(request);
            SortedMap<String,Object> sortedMap = new TreeMap<String,Object>(map);
            String sign = WxPayUtil.createSign(sortedMap, key);
            request.setSign(sign);



            String param = XMLUtil.beanToXml(request);
            HttpClient httpClient = new HttpClient();
            HttpResponse response = httpClient.post(unified_order, param);
            String body = response.getBody();
            WxUnifiedOrderResponse unifiedOrderResponse = XMLUtil.xmlToBean(body, WxUnifiedOrderResponse.class);
            String returnCode = unifiedOrderResponse.getReturnCode();
            if(WxPayReturnCodeEnum.FAIL.getValue().equals(returnCode)){
                throw new WxException(body);
            }
            if(StringUtil.isEmpty(unifiedOrderResponse.getPrepayId())){
                throw new WxException(body);
            }

            /*
            返回给客户端的签名
            参数，一共5个 + 签名
            签名参数：paySign。参与签名的参数有appId, timeStamp, nonceStr, package, signType。
             */
            SortedMap<String, Object> responseSign = new TreeMap<String, Object>();
            //1 - appId
            responseSign.put("appId", request.getAppid());
            //2 - timeStamp，签名用大写timeStamp，提交时用小写timestamp，受不鸟...
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            responseSign.put("timeStamp", timestamp);
            //3 - nonceStr
            responseSign.put("nonceStr", nonceStr);
            //4
            String package_ = "prepay_id=" + unifiedOrderResponse.getPrepayId();
            responseSign.put("package", package_);
            //5 - signType
            String signType = "MD5";
            responseSign.put("signType", signType);
            //6
            String paySign = WxPayUtil.createSign(responseSign, key);

            WxUnifiedOrderResult result = new WxUnifiedOrderResult();
            result.setNonceStr(nonceStr);
            result.setPg(package_);
            result.setSignType(signType);
            result.setTimeStamp(timestamp);
            result.setPaySign(paySign);
            return result;
        } catch (IOException e) {
            throw new WxException(e);
        } catch (HttpException e) {
            throw new WxException(e);
        }
    }

    @Override
    public WxPayNotifyResponse notifyResponse(HttpServletRequest request) throws WxException {
        try {
            String xml = FileUtil.load(request.getInputStream());
            return XMLUtil.xmlToBean(xml, WxPayNotifyResponse.class);
        } catch (IOException e) {
            throw new WxException(e);
        }
    }

    @Override
    public String notifyReturn() throws WxException {
        WxPayNotifyReturn aReturn = new WxPayNotifyReturn();
        try {
            return XMLUtil.beanToXml(aReturn);
        } catch (JsonProcessingException e) {
            throw new WxException(e);
        }
    }
}
