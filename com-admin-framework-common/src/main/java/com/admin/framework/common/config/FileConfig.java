package com.admin.framework.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 文件访问配置
 * @author ZSW
 * @date 2019/3/2
 */
//@Configuration
public class FileConfig {


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置server虚拟路径，handler为jsp中访问的目录，locations为files相对应的本地路径
//        registry.addResourceHandler("/files/**").addResourceLocations("file:///"+fileBase);
    }

}
