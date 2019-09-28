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

    public ExcelEntity(String sheetName,List<Map<String,Object>> dataMap){
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
            map.forEach((k,v)->{
                String value = v == null ? "" : v.toString();
                if(!keys.contains(k)){
                    keys.add(k);
                }
                values.add(value);
            });
            result.add(values);
        }
        result.add(0,keys);
        this.data = result;
    }

}
