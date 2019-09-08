package com.admin.framework.component.sms;

import com.admin.framework.component.utils.ArrayUtil;
import com.admin.framework.component.utils.ListUtil;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @Author zsw
 * @Description
 * @Date Create in 19:41 2019\8\5 0005
 */
public class TencentSms extends SmsClient {

    public TencentSms(SmsConfig config) throws SmsException {
        super(config);
    }

    @Override
    public SmsResult sendSingleByTemplateId(String phone, String templateId, List<String> params, String extend, String ext,String nationCode) {
        SmsResult smsResult = new SmsResult();
        SmsSingleSender sender = new SmsSingleSender(Integer.valueOf(config.getAppId()),config.getAppKey());
        try {
            String[] param = params.toArray(new String[params.size()]);
            SmsSingleSenderResult send = sender.sendWithParam(nationCode, phone, Integer.valueOf(templateId), param, config.getSmsSign(), extend, ext);
            int result = send.result;
            if(result == 0){
                smsResult.setSuccess(true);
            }else{
                smsResult.setSuccess(false);
                smsResult.setMsg(send.errMsg);
            }
            smsResult.setData(send.ext);
        } catch (HTTPException e) {
            e.printStackTrace();
            smsResult.setSuccess(false);
            smsResult.setMsg(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            smsResult.setSuccess(false);
            smsResult.setMsg(e.getMessage());
        }
        return smsResult;
    }

    @Override
    public SmsResult sendSingleByTemplateId(String phone, String templateId, List<String> params, String extend, String ext) {
        return sendSingleByTemplateId(phone,templateId,params,extend,ext,"86");
    }

    @Override
    public SmsResult sendSingleByTemplateId(String phone, String templateId, List<String> params, String extend) {
        return sendSingleByTemplateId(phone,templateId,params,extend,"","86");
    }

    @Override
    public SmsResult sendSingleByTemplateId(String phone, String templateId, List<String> params) {
        return sendSingleByTemplateId(phone,templateId,params,"","","86");
    }

    @Override
    public SmsResult sendSingleByTemplateId(String phone, List<String> params) {
        return sendSingleByTemplateId(phone,config.getDefaultTemplateId(),params);
    }


}
