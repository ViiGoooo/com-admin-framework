package com.admin.framework.wechat.service.impl;

import com.admin.framework.component.utils.Sha1Util;
import com.admin.framework.wechat.service.WxConfigService;

import java.util.Arrays;

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

}
