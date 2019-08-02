package com.admin.framework.component.excel;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ZSW
 * @date 2019/1/22
 */
@Data
public class ExcelEntity {

    ExcelEntity(String sheetName,List<Map<String,Object>> dataMap){
        this.sheetName = sheetName;
        this.dataMap = dataMap;
        setData();
    }


    private String sheetName;
    private List<Map<String,Object>> dataMap;
    private List<List<String>> data;


    private void setData(){
        List<List<String>> result = new ArrayList<>();
        List<String> keys = new ArrayList<>();
        for(Map<String,Object> map : dataMap){
            List<String> values = new ArrayList<>();
            for(Map.Entry<String,Object> en:map.entrySet()){
                String key = en.getKey();
                String value = en.getValue() == null ? "" : en.getValue().toString();

                if(!keys.contains(key)){
                    keys.add(key);
                }
                values.add(value);
            }
            result.add(keys);
            result.add(values);
        }
        this.data = result;
    }

}
