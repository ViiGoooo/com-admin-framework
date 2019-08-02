package com.admin.framework.orm.dojo;

import com.admin.framework.component.utils.StringUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ZSW
 * @date 2019/4/7
 */
@Data
public class Conditions {
    private String baseSql;

    private List<Condition> conditions = new ArrayList<>();

    /**
     * 添加条件
     * @param condition
     */
    public void add(Condition condition){
        this.conditions.add(condition);
    }

    /**
     * 获取sql
     * @return
     */
    public List<String> getSql(){
        List<String> executeSql = new ArrayList<>();
        for(Condition condition:conditions){
            String sql = condition.getSql();
            if(StringUtil.isEmpty(sql)){
                continue;
            }
            executeSql.add(sql);
        }
        return executeSql;
    }

}
