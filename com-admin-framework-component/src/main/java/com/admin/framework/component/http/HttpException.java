package com.admin.framework.component.http;

/**
 *
 * @author ZSW
 * @date 2019/8/8
 */
public class HttpException extends Exception {

    /**
     * 无参构造函数
     * */
    public HttpException(){
        super();
    }

    /**
     * 用详细信息指定一个异常
     */
    public HttpException(String message){
        super(message);
    }

    /**
     * 用指定的详细信息和原因构造一个新的异常
     * @param message
     * @param cause
     */
    public HttpException(String message, Throwable cause){
        super(message,cause);
    }

    /**
     * 用指定原因构造一个新的异常
     * @param cause
     */
    public HttpException(Throwable cause) {
        super(cause);
    }
    
}
