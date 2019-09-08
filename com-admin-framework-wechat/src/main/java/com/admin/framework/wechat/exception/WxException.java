package com.admin.framework.wechat.exception;

import com.admin.framework.component.utils.JSONUtil;
import com.admin.framework.wechat.entity.WxResult;
import lombok.Data;

import java.util.List;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:58 2019\8\8 0008
 */
@Data
public class WxException extends Exception {

    private String code;
    private String msg;

    /*无参构造函数*/
    public WxException(){
        super();
    }

    //用详细信息指定一个异常
    public WxException(String message){
        super(message);
        this.msg = message;
    }

    //用详细信息指定一个异常
    public WxException(List<String> message){
        super(JSONUtil.objToJsonStr(message));
    }

    //用详细信息指定一个异常
    public WxException(WxResult wxResult){
        super(wxResult.getErrmsg());
        this.code = wxResult.getErrcode();
        this.msg = wxResult.getErrmsg();
    }

    //用指定的详细信息和原因构造一个新的异常
    public WxException(String message, Throwable cause){
        super(message,cause);
        this.msg = message;
    }

    //用指定原因构造一个新的异常
    public WxException(Throwable cause) {
        super(cause);
    }

}
