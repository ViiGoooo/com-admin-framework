package com.admin.framework.wechat.service.impl;

import com.admin.framework.component.utils.RandomUtil;
import com.admin.framework.component.utils.Sha1Util;
import com.admin.framework.component.utils.StringUtil;
import com.admin.framework.wechat.entity.WxJsConfig;
import com.admin.framework.wechat.exception.WxException;
import com.admin.framework.wechat.service.WxConfigService;
import com.admin.framework.wechat.utils.WxPayUtil;

import java.util.*;

/**
 * @Author zsw
 * @Description
 * @Date Create in 16:18 2019\8\23 0023
 */
public class WxConfigServiceImpl implements WxConfigService {



    @Override
    public boolean checkSignature(String token,String signature, String timestamp, String nonce){
        String [] arr = {token, timestamp, nonce};
        Arrays.sort(arr);
        StringBuffer sbf = new StringBuffer();
        for (String str : arr){
            sbf.append(str);
        }
        String getSignature = Sha1Util.encode(sbf.toString());
        return signature.equals(getSignature);
    }



    @Override
    public WxJsConfig jsConfig(String ticket, String url) throws WxException {
        String nonceStr = RandomUtil.getAllRandomStr(32);
        String timestamp = System.currentTimeMillis() / 1000 + "";

        SortedMap<String, Object> signParams = new TreeMap<String, Object>();
        /*
         signature:参与签名的字段包括
            noncestr（随机字符串）
            有效的jsapi_ticket, timestamp（时间戳）
            url（当前网页的URL，不包含#及其后面部分）
         */
        signParams.put("jsapi_ticket", ticket);
        signParams.put("noncestr", nonceStr);
        signParams.put("timestamp", timestamp);
        signParams.put("url", url);
        String signature = createSign(signParams);
        if(StringUtil.isEmpty(signature)){
            throw new WxException("签名失败");
        }

        WxJsConfig config = new WxJsConfig();
        config.setTimestamp(timestamp);
        config.setNonceStr(nonceStr);
        config.setSignature(signature);
        return config;
    }


    /**
     * 微信支付签名算法sign
     * @param parameters
     * @return
     */
    private String createSign(SortedMap<String,Object> parameters){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            sb.append(k + "=" + v + "&");
        }
        String param = sb.substring(0, sb.lastIndexOf("&"));
        String sign = Sha1Util.encode(param);
        System.out.println("签名参数:"+ param);
        System.out.println("签名结果:"+ sign);
        return sign;
    }


}
