package com.admin.framework.component.logistics;

import com.admin.framework.component.BaseException;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:44 2019\9\19 0019
 */
public class LogisticsException extends BaseException {


    public LogisticsException(String message) {
        super(message);
    }

    public LogisticsException(String message, Integer code) {
        super(message, code);
    }

    public LogisticsException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogisticsException(Throwable cause) {
        super(cause);
    }

}
