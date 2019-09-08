package com.admin.framework.component.sms;

import lombok.Data;

/**
 * @Author zsw
 * @Description
 * @Date Create in 14:38 2019\8\21 0021
 */
@Data
public class SmsConfig {

    private String appId;
    private String appKey;
    private String smsSign;
    private String defaultTemplateId;
}
