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


    public Page(Integer pageSize,Integer currentPage){
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public void setTotalPage(Integer total,Integer pageSize){
        Integer totalPage = 0;
        if (total != null && total > 0) {
            int pages = total / pageSize;
            totalPage = total % pageSize > 0 ? ++pages : pages;
        }
        this.totalPage = totalPage;
    }



}
