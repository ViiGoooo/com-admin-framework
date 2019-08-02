package com.admin.framework.orm.support;

/**
 *
 * @author ZSW
 * @date 2018/12/28
 */
public abstract class BaseDialect {

    /**
     * 得到分页sql
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    public abstract String getLimitString(String sql, int offset, int limit);

    /**
     * 得到分页sql
     * @param sql
     * @return
     */
    public abstract String getCountString(String sql);


}
