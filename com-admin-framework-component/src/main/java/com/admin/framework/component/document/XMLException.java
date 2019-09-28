package com.admin.framework.component.document;

import com.admin.framework.component.BaseException;

/**
 * @Author zsw
 * @Description
 * @Date Create in 15:22 2019\9\19 0019
 */
public class XMLException  extends BaseException {

    public XMLException(String message) {
        super(message);
    }

    public XMLException(String message, Integer code) {
        super(message, code);
    }

    public XMLException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLException(Throwable cause) {
        super(cause);
    }

}
