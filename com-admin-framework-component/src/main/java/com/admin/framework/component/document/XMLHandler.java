package com.admin.framework.component.document;

import org.apache.xmlbeans.XmlException;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zsw
 * @Description
 * @Date Create in 15:01 2019\9\19 0019
 */
public class XMLHandler {
    private Element root;

    public XMLHandler(File file) throws XmlException {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            root = document.getRootElement();
        }catch (DocumentException e) {
            e.printStackTrace();
            throw new XmlException("初始化dom树失败");
        }
    }

    public XMLHandler(InputStream inputStream) throws XmlException {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            root = document.getRootElement();
        }catch (DocumentException e) {
            e.printStackTrace();
            throw new XmlException("初始化dom树失败");
        }
    }

    public XMLHandler(String xmlStr) throws XmlException {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new ByteArrayInputStream(xmlStr.getBytes("UTF-8")));
            root = document.getRootElement();
        }catch (DocumentException e) {
            e.printStackTrace();
            throw new XmlException("初始化dom树失败");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new XmlException("初始化dom树失败");
        }
    }

    /**
     * 获取指定的标签节点
     * @param tag
     * @return
     */
    public List<Element> getList(String tag){
        List<Element> result = new ArrayList<>();
        List<Element> elements = root.elements();
        elements.forEach(e->{
            String name = e.getName();
            if(tag.equals(name)){
                result.add(e);
            }
        });
        return result;
    }






}
