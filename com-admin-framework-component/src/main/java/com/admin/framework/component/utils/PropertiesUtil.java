package com.admin.framework.component.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author ZSW
 * @date 2018/11/20
 */
public class PropertiesUtil<T> {

    /**
     * 获取配置文件
     * @param propertiesPath
     * @return
     */
    public static Map propertiesToMap(String propertiesPath){
        Yaml yaml = new Yaml();
        try {
            File file = new File(propertiesPath);
            if(!file.exists()){
                throw new RuntimeException("配置文件不存在");
            }
            FileInputStream in = new FileInputStream(propertiesPath);
            Map load = yaml.load(in);
            return load;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
