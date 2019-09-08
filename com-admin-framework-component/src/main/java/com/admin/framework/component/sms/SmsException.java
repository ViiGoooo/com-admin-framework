package com.admin.framework.component.sms;


import com.admin.framework.component.BaseException;

/**
 * @Author zsw
 * @Description
 * @Date Create in 16:38 2019\8\21 0021
 */
public class SmsException extends BaseException {


    public SmsException(String message) {
        super(message);
    }

    public SmsException(String message,Integer code) {
        super(message,code);
    }

    public SmsException(String message, Throwable cause){
        super(message,cause);
    }

    public SmsException(Throwable cause) {
        super(cause);
    }


}
