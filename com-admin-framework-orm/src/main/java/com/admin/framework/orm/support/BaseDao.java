package com.admin.framework.orm.support;

import com.admin.framework.common.entity.Page;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ZSW
 * @date 2018/11/18
 */
public interface BaseDao<T> {
    /**
     * 自定义查询
     * @param sql
     * @return
     */
    List<T> query(List<String> sql);

    /**
     * 分页
     * @param page
     * @param query
     * @return
     */
    List<T> page(Page<T> page, Map query);

    /**
     * g更新
     * @param obj
     * @return
     */
    int update(T obj);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int removeById(Integer id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchRemovedByIds(List<Integer> ids);

    /**
     * 保存
     * @param obj
     * @return
     */
    int save(T obj);

    /**
     * 批量保存
     * @param obj
     * @return
     */
    int batchSave(List<T> obj);

}
