package com.admin.framework.orm.exception;

/**
 * Created by ZSW on 2019/6/27.
 */
public class DojoException extends RuntimeException {

    private static final long serialVersionUID = -8342427634552193107L;

    private int code;
    private String message;


    public DojoException() {
        super();
    }
    public DojoException(String message) {
        this.message = message;
    }

    public DojoException(String message, Integer code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public DojoException setCode(int code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public DojoException setMessage(String message) {
        this.message = message;
        return this;
    }

}
