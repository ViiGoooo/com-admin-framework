package com.admin.framework.common.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询类
 * @author ZSW
 * @date 2018/12/28
 */
@Data
public class Page<T> extends Resp{

    /**
     *当前页
     */
    private Integer currentPage = 1;
    /**
     *  每页显示的总条数
     */
    private Integer pageSize = 10;
    /**
     *  总条数
     */
    private Integer total = 0;
    /**
     * 总页数
     */
    private Integer totalPage;



    public void setTotalPage(Integer total,Integer pageSize){
        Integer totalPage = 0;
        if (total != null && total > 0) {
            int pages = total / pageSize;
            totalPage = total % pageSize > 0 ? ++pages : pages;
        }
        this.totalPage = totalPage;
    }

    /**
     * 错误处理
     * @return
     */
    public static Page empty(){
        Page page = new Page();
        return page;
    }

    /**
     * 错误处理
     * @return
     */
    public static Page error(){
        return error(Resp.DEFAULT_ERROR_MSG);
    }

    /**
     * 错误处理
     * @param msg 错误消息
     * @return
     */
    public static Page error(String msg){
        return error(DEFAULT_ERROR_CODE,msg);
    }

    /**
     * 错误处理
     * @param code 错误码
     * @param msg 错误消息
     * @return
     */
    public static Page error(Integer code, String msg){
        Page page = new Page();
        page.setCode(code);
        page.setMsg(msg);
        page.setSuccess(false);
        return page;
    }

}
