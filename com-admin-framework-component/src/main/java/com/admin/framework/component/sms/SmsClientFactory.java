package com.admin.framework.component.sms;

import java.util.Random;

/**
 * @Author zsw
 * @Description
 * @Date Create in 19:37 2019\8\5 0005
 */
public class SmsClientFactory {

    /**
     * 获取发送短信的客户端
     * @param smsEnum
     * @param config
     * @return
     */
    public static SmsClient getClient(SmsEnum smsEnum, SmsConfig config) throws SmsException {
        SmsClient smsClient = null;
        switch (smsEnum){
            case ALIBABA:
                break;
            case TENCENT:
                smsClient = new TencentSms(config);
                break;
        }
        return smsClient;
    }

    /**
     * 获取验证码
     * @param charCount
     * @return
     */
    public static String getVerifyCode(int charCount){
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < charCount; i++) {
            code.append(new Random().nextInt(10));
        }
        return code.toString();
    }

}
