package com.admin.framework.orm.constant;

/**
 * Created by ZSW on 2019/6/27.
 */
public enum ConditionExpressionEnum {
    /**
     * 大于
     */
    GREATER(" column > value "),
    /**
     * 大于等于
     */
    GREATER_EQUALS(" column >= value "),
    /**
     * 等于
     */
    EQUALS(" column = value "),
    /**
     * 小于
     */
    LESS(" column  < value "),
    /**
     * 小于等于
     */
    LESS_EQUALS(" column <= value "),
    /**
     * 模糊查询
     */
    CONTAIN(" column like '%'||value||'%' "),
    /**
     * 以？开头
     */
    START(" column like value||'%' "),
    /**
     * 以？结束
     */
    END(" column like '%'||value "),
    /**
     * 在...之间
     */
    BETWEEN(" column between value and value "),
    /**
     * 为空
     */
    NULL(" column is null "),
    /**
     * 不为空
     */
    NOT_NULL(" column is not null "),
    /**
     * 顺排
     */
    ORDER_ASC(" order by column asc "),
    /**
     * 逆序
     */
    ORDER_DESC(" order by column desc "),
    ;


    ConditionExpressionEnum(String value){
        this.value = value;
    }

    private String value;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
