package com.admin.framework.orm.support;

import com.admin.framework.common.entity.Page;
import com.admin.framework.component.utils.MapUtil;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author ZSW
 * @date 2018/12/28
 */
@Component
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class SqlInterceptor implements Interceptor{


    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        ParameterHandler parameterHandler = statementHandler.getParameterHandler();
        BoundSql boundSql = statementHandler.getBoundSql();

        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        Object paramObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");
//        String name = paramObject.getClass().getName();
        // 没有分页参数
        Page<?> page = null;
        try {
            Map map = (Map) paramObject;
            page = (Page) map.get("page");
            Map param = (Map) map.get("query");
            Integer pageSize = MapUtil.getInteger(param, "pageSize");
            Integer pageNumber = MapUtil.getInteger(param, "pageNumber");
            page.setCurrentPage(pageNumber);
            page.setPageSize(pageSize);
        }catch (Exception e){
            return invocation.proceed();
        }
        if(page == null){
            return invocation.proceed();
        }
        Connection connection = (Connection) invocation.getArgs()[0];
        Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
        BaseDialect dialect = new MysqlDialect();
        String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        String countSql = dialect.getCountString(originalSql);
        int total = getTotal(parameterHandler, connection, countSql);
//        Page<?> page = (Page<?>) paramObject;
        page.setTotal(total);
        String limitSql = dialect.getLimitString(originalSql, page.getCurrentPage()-1, page.getPageSize());
        metaStatementHandler.setValue("delegate.boundSql.sql",limitSql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 获取总计录
     *
     * @param parameterHandler
     * @param connection
     * @param countSql
     * @return
     * @throws Exception
     */
    private int getTotal(ParameterHandler parameterHandler, Connection connection, String countSql) throws Exception {
        PreparedStatement prepareStatement = connection.prepareStatement(countSql);
        parameterHandler.setParameters(prepareStatement);
        ResultSet rs = prepareStatement.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        prepareStatement.close();
        return count;
    }

}
