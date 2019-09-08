package com.admin.framework.component.http;

import com.admin.framework.component.BaseException;

/**
 *
 * @author ZSW
 * @date 2019/8/8
 */
public class HttpException extends BaseException {

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message,Integer code) {
        super(message,code);
    }

    public HttpException(String message, Throwable cause){
        super(message,cause);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }


}
