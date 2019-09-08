package com.admin.framework.component.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

/**
 * xml操作工具类
 * @Author zsw
 * @Description
 * @Date Create in 16:43 2019\8\14 0014
 */
public class XMLUtil<T> {

    private static XmlMapper mapper = new XmlMapper();

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * xml转对象
     * @param xml
     * @param <T>
     * @return
     */
    public static<T> T xmlToBean(String xml,Class<T> clz) throws IOException {
        return mapper.readValue(xml, clz);
    }

    /**
     * 对象转xml
     * @param t
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static<T> String beanToXml(T t) throws JsonProcessingException {
        return mapper.writeValueAsString(t);
    }
}
