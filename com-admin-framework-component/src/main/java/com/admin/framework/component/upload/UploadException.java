package com.admin.framework.component.upload;

import com.admin.framework.component.BaseException;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:32 2019\8\16 0016
 */
public class UploadException extends BaseException {

    public UploadException(String message) {
        super(message);
    }

    public UploadException(String message,Integer code) {
        super(message,code);
    }

    public UploadException(String message, Throwable cause){
        super(message,cause);
    }

    public UploadException(Throwable cause) {
        super(cause);
    }

}
