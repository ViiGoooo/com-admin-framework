package com.admin.framework.component;

/**
 * @Author zsw
 * @Description
 * @Date Create in 16:55 2019\8\21 0021
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = -8342427634552193107L;

    private int code;
    private String message;


    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
        this.message = message;
    }


    /**
     * 用指定的详细信息和原因构造一个新的异常
     * @param message
     * @param cause
     */
    public BaseException(String message, Throwable cause){
        super(message,cause);
    }

    /**
     * 用指定原因构造一个新的异常
     * @param cause
     */
    public BaseException(Throwable cause) {
        super(cause);
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
