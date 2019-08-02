package com.admin.framework.orm.support;


/**
 *
 * @author ZSW
 * @date 2018/12/28
 */
public class MysqlDialect extends BaseDialect {

    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return MysqlPageHepler.getLimitString(sql, offset, limit);
    }

    @Override
    public String getCountString(String sql) {
        return MysqlPageHepler.getCountString(sql);
    }
}
