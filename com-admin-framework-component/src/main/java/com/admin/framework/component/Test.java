package com.admin.framework.component;

import com.admin.framework.component.utils.FileUtil;
import com.admin.framework.component.utils.JSONUtil;
import com.admin.framework.component.utils.ListUtil;
import com.admin.framework.component.utils.XMLUtil;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * @Author zsw
 * @Description
 * @Date Create in 16:00 2019\9\3 0003
 */
public class Test {




    public static void main(String[] args) throws IOException {
    }

    static String sql = "INSERT INTO `regions`(`id`, `name`, `parent_id`, `has_children`,`level`) VALUES (%s, '%s', %s, %s,%s)";
    private static String createSql(Integer id,String name,Integer parent,Integer hasChildren,Integer level){
        String format = String.format(sql, id, name, parent, hasChildren,level);
        return format;
    }



    public static void regions(){
        String load = FileUtil.load("C:\\Users\\Administrator\\Desktop\\成氏之家\\city.json","GBK");
//        String load = "{\"location\":{\"country_regions\":[{\"name\":\"aaa\"}]}}";
        Map map = JSONUtil.jsonToObj(load, Map.class);
        Map location = (Map) map.get("location");
        List<Map> country_regions = (List<Map>) location.get("country_regions");
        List<String> sqls = new ArrayList<>();
        Integer id = 100000;
        for(Map country:country_regions){
            String countryName = country.get("name").toString();
            Object state = country.get("state");
            String countrySql = createSql(id, countryName, 0, 1,1);
            Integer countryId = Integer.valueOf(id.toString());
            sqls.add(countrySql);
            id++;
            if(state instanceof ArrayList){
                List<Map> stateMapList = (List<Map>) state;
                for(Map stateMap:stateMapList){
                    String stateName = stateMap.get("name").toString();
                    String stateSql = createSql(id, stateName, countryId, 1,2);
                    Integer stateId = id;
                    sqls.add(stateSql);
                    id++;
                    Object city = stateMap.get("city");
                    if(city instanceof ArrayList){
                        List<Map> cityList = (List<Map>) stateMap.get("city");
                        for(Map cityMap:cityList){
                            String cityName = cityMap.get("name").toString();
                            Integer cityId = id;
                            String citySql = createSql(id, cityName, stateId, 1,3);
                            sqls.add(citySql);
                            id++;
                            Object region = cityMap.get("Region");
                            if(region instanceof ArrayList){
                                List<Map> regionMapList = (List<Map>) cityMap.get("Region");
                                if(!ListUtil.isEmpty(regionMapList)){
                                    for(Map regionMap:regionMapList){
                                        String regionName = regionMap.get("name").toString();
                                        String regionSql = createSql(id, regionName, cityId, 0, 4);
                                        sqls.add(regionSql);
                                        id++;
                                    }
                                }
                            }else{
                                if(region != null){
                                    Map regionMap = (Map) cityMap.get("Region");
                                    String regionName = regionMap.get("name").toString();
                                    String regionSql = createSql(id, regionName, cityId, 0, 4);
                                    sqls.add(regionSql);
                                    id++;
                                }
                            }


                        }
                    }else{
                        Map cityMap = (Map) stateMap.get("city");
                        if(cityMap != null){
                            String cityName = cityMap.get("name").toString();
                            Integer cityId = id;
                            String citySql = createSql(id, cityName, stateId, 1,3);
                            sqls.add(citySql);
                            id++;
                            Object region = cityMap.get("Region");
                            if(region instanceof ArrayList){
                                List<Map> regionMapList = (List<Map>) cityMap.get("Region");
                                if(!ListUtil.isEmpty(regionMapList)){
                                    for(Map regionMap:regionMapList){
                                        String regionName = regionMap.get("name").toString();
                                        String regionSql = createSql(id, regionName, cityId, 0, 4);
                                        sqls.add(regionSql);
                                        id++;
                                    }
                                }
                            }else{
                                if(region != null){
                                    Map regionMap = (Map) cityMap.get("Region");
                                    String regionName = regionMap.get("name").toString();
                                    String regionSql = createSql(id, regionName, cityId, 0, 4);
                                    sqls.add(regionSql);
                                    id++;
                                }
                            }
                        }

                    }

                }
            }else{
                Map stateMap = (Map) state;
                if(stateMap != null){
                    Object city = stateMap.get("city");
                    if(city instanceof ArrayList){
                        List<Map> cityList = (List<Map>) stateMap.get("city");
                        for(Map cityMap:cityList){
                            String cityName = cityMap.get("name").toString();
                            Integer cityId = id;
                            String citySql = createSql(id, cityName, countryId, 1,2);
                            sqls.add(citySql);
                            id++;
                            Object region = cityMap.get("Region");
                            if(region instanceof ArrayList){
                                List<Map> regionMapList = (List<Map>) cityMap.get("Region");
                                if(!ListUtil.isEmpty(regionMapList)){
                                    for(Map regionMap:regionMapList){
                                        String regionName = regionMap.get("name").toString();
                                        String regionSql = createSql(id, regionName, cityId, 0, 3);
                                        sqls.add(regionSql);
                                        id++;
                                    }
                                }
                            }else{
                                if(region != null){
                                    Map regionMap = (Map) cityMap.get("Region");
                                    String regionName = regionMap.get("name").toString();
                                    String regionSql = createSql(id, regionName, cityId, 0, 3);
                                    sqls.add(regionSql);
                                    id++;
                                }
                            }

                        }
                    }else{
                        Map cityMap = (Map) stateMap.get("city");
                        if(cityMap != null){
                            String cityName = cityMap.get("name").toString();
                            Integer cityId = id;
                            String citySql = createSql(id, cityName, countryId, 1,2);
                            sqls.add(citySql);
                            id++;
                            Object region = cityMap.get("Region");
                            if(region instanceof ArrayList){
                                List<Map> regionMapList = (List<Map>) cityMap.get("Region");
                                if(!ListUtil.isEmpty(regionMapList)){
                                    for(Map regionMap:regionMapList){
                                        String regionName = regionMap.get("name").toString();
                                        String regionSql = createSql(id, regionName, cityId, 0, 3);
                                        sqls.add(regionSql);
                                        id++;
                                    }
                                }
                            }else{
                                if(region != null){
                                    Map regionMap = (Map) cityMap.get("Region");
                                    String regionName = regionMap.get("name").toString();
                                    String regionSql = createSql(id, regionName, cityId, 0, 3);
                                    sqls.add(regionSql);
                                    id++;
                                }
                            }
                        }
                    }
                }

            }
        }

        for(String sql:sqls){
            System.out.println(sql+";");
        }
    }
}

