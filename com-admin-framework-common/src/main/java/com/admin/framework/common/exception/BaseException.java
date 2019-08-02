package com.admin.framework.common.exception;

import com.admin.framework.common.constant.BaseResp;
import com.admin.framework.common.entity.Resp;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ZSW
 * @date 2018/8/11
 */
@ControllerAdvice
public class BaseException {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Resp defaultExceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        return BaseResp.UNKNOWN_ERROR;
    }
}
