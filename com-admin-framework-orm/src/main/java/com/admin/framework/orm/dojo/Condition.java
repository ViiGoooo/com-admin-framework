package com.admin.framework.orm.dojo;

import com.admin.framework.component.utils.ArrayUtil;
import com.admin.framework.component.utils.ReflectUil;
import com.admin.framework.component.utils.StringUtil;
import com.admin.framework.orm.constant.ConditionExpressionEnum;
import com.admin.framework.orm.exception.DojoException;
import lombok.Data;

import java.util.List;

/**
 *
 * @author ZSW
 * @date 2019/4/7
 */
@Data
public class Condition<T> {

    private final String COLUMN_KEY = "column";
    private final String VALUE_KEY = "value";

    private String column;
    private ConditionExpressionEnum expression;
    private Object[] value;
    private Class<T> entity;
    private String alias;

    public Condition(Class<T> entity,String alias,String column,ConditionExpressionEnum expression,Object... value){
        if(isSqlInject(column)){
            throw new DojoException("参数不合法");
        }
        if(value != null){
            for(int x = 0 ; x < value.length ; x++){
                if(isSqlInject(value[x].toString())){
                    throw new DojoException("参数不合法");
                }
            }
        }
        this.column = column;
        this.expression = expression;
        this.value = value;
        this.entity = entity;
        this.alias = alias;
    }


    public String getSql(){
        String sql = this.expression.getValue();
        sql = sql.replace(COLUMN_KEY,column);
        if(!ArrayUtil.isEmpty(this.value)){
            for(int x = 0 ; x < this.value.length ; x++){
                String v = this.value[x].toString();
                int i = sql.indexOf(VALUE_KEY);
                sql = StringUtil.replacePosition(sql,VALUE_KEY,v,i);
            }
        }
        return sql;
    }


    private boolean isCurrentField(String column){
        List<String> allField = ReflectUil.getAllField(entity);
        return allField.contains(column);
    }


    private boolean isSqlInject(String injectStr) {
        String injStr = "'|and|exec|create|insert|select|delete|update|count|*|%|chr|mid|master|truncate|drop|char|declare|;|or|-|+|,";
        String injStrArr[] = injStr.split("\\|");
        injectStr = injectStr.toLowerCase();
        for (int i = 0; i < injStrArr.length; i++) {
            if (injectStr.indexOf(injStrArr[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
    }
}
