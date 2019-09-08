package com.admin.framework.component.cache;

import com.admin.framework.component.BaseException;

/**
 *
 * @author ZSW
 * @date 2019/1/17
 */
public class CacheException extends BaseException {

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message,Integer code) {
        super(message,code);
    }

    public CacheException(String message, Throwable cause){
        super(message,cause);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }


}
