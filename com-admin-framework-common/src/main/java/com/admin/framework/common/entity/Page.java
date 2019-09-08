package com.admin.framework.common.entity;

import com.admin.framework.component.utils.MapUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分页查询类
 * @author ZSW
 * @date 2018/12/28
 */
@Data
public class Page<T>{

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
    private Integer totalPage = 0;

    private List<T> data;

    public Page(Map param){
        this.pageSize = MapUtil.getInteger(param, "pageSize");
        this.currentPage = MapUtil.getInteger(param, "currentPage");
    }

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

    public void setData(List data){
        this.data = data;
    }

}
