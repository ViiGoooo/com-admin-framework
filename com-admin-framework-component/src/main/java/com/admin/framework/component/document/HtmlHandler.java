package com.admin.framework.component.document;


import com.admin.framework.component.utils.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;

/**
 * html处理类
 * @author ZSW
 * @date 2019/2/21
 */
public class HtmlHandler {

    Document document;

    public Document getDocument(File file){
        String load = FileUtil.load(file);
        return getDocument(load,ElementType.CONTENT);
    }


    public Document getDocument(String str,ElementType elementType){
        try {
            if(ElementType.CONTENT == elementType){
                document = Jsoup.parse(str);
            }
            if(ElementType.URL == elementType){
                document = Jsoup.connect(str).get();
            }
            if(ElementType.FILE == elementType){
                String load = FileUtil.load(str);
                document = Jsoup.parse(load);
            }
            if(document == null){
                throw new RuntimeException("转化html失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return document;
    }


    public static void main(String[] args) {

        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<root>\n" +
                "\n" +
                "    <sql id=\"getList\">\n" +
                "        select * from coupon\n" +
                "    </sql>\n" +
                "\n" +
                "</root>\n";

        HtmlHandler htmlHandler = new HtmlHandler();
        Document document = htmlHandler.getDocument(xmlStr, ElementType.CONTENT);
        Elements children = document.children();
        String html = children.html();
        System.out.println(html);
    }



}
