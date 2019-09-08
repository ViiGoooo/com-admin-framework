package com.admin.framework.component.sms;

import com.admin.framework.component.utils.ListUtil;
import com.admin.framework.component.utils.NotNullVerifyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @Author zsw
 * @Description
 * @Date Create in 19:30 2019\8\5 0005
 */
public abstract class SmsClient {

    protected Logger logger = LoggerFactory.getLogger(SmsClient.class);

    protected SmsConfig config;

    public SmsClient(SmsConfig config) throws SmsException {
        List<String> verify = NotNullVerifyUtil.verify(config);
        if(!ListUtil.isEmpty(verify)){
            throw new SmsException(verify.get(0));
        }
        this.config = config;
    }

    /**
     * 指定模板单发
     * @param nationCode
     * @param phone
     * @param templateId
     * @param params
     * @param extend
     * @param ext
     * @return
     */
    public abstract SmsResult sendSingleByTemplateId(String phone, String templateId,List<String> params,String extend,String ext,String nationCode);
    public abstract SmsResult sendSingleByTemplateId(String phone, String templateId,List<String> params,String extend,String ext);
    public abstract SmsResult sendSingleByTemplateId(String phone, String templateId,List<String> params,String extend);
    public abstract SmsResult sendSingleByTemplateId(String phone, String templateId,List<String> params);
    public abstract SmsResult sendSingleByTemplateId(String phone, List<String> params);


}
