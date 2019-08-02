package com.admin.framework.orm.dojo;

import com.admin.framework.common.utils.SpringBeanUtil;
import com.admin.framework.component.utils.ListUtil;
import com.admin.framework.component.utils.StringUtil;
import com.admin.framework.orm.constant.ConditionExpressionEnum;
import com.admin.framework.orm.support.BaseDao;

import java.util.List;

/**
 *
 * @author ZSW
 * @date 2019/3/20
 */
public abstract class BaseDojo<T> {

    protected BaseDao dao;
    private Class<T> entityClz;
    private Conditions conditions;
    private String alias;
    public BaseDojo(Class daoClz, Class<T> entityClz,String alias){
        conditions = new Conditions();
        dao = (BaseDao) SpringBeanUtil.getBean(daoClz);
        this.entityClz = entityClz;
        this.alias = alias;
    }

    /**
     * 添加sql语句
     * @param column
     * @param expression
     * @param params
     */
    public void addSql(String column, ConditionExpressionEnum expression, Object... params){
        Condition condition = new Condition(entityClz,alias,StringUtil.humpToUnderline(column),expression, params);
        conditions.add(condition);
    }

    /**
     * 获取单个对象
     * @return
     */
    public T get(){
        List<String> sql = conditions.getSql();
        List<T> query = dao.query(sql);
        if(ListUtil.isEmpty(query)){
            return null;
        }
        return query.get(0);
    }

    /**
     * 查询列表
     * @return
     */
    public List<T> list(){
        return dao.query(conditions.getSql());
    }


}
