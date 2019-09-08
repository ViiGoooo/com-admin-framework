package com.admin.framework.component.sms;

import lombok.Data;

/**
 * @Author zsw
 * @Description
 * @Date Create in 19:35 2019\8\5 0005
 */
@Data
public class SmsResult<T> {

    private boolean success;
    private String msg = "";
    private T data;

}
