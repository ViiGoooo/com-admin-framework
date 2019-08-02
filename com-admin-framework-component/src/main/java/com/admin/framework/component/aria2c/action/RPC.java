package com.admin.framework.component.aria2c.action;

import com.admin.framework.component.aria2c.contanst.Methods;
import com.admin.framework.component.aria2c.entity.Param;
import com.admin.framework.component.http.HttpClient;
import com.admin.framework.component.utils.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZSW on 2019/2/25.
 */
public class RPC {
    private HttpClient httpClient = new HttpClient();

    public RPC(String baseUrl){
        this.baseUrl = baseUrl;
    }

    private String baseUrl;


    /**
     * 此方法添加了新的下载。
     * uris是指向同一资源的HTTP / FTP / SFTP / BitTorrent URI（字符串）数组。
     * 如果混合指向不同资源的URI，则下载可能会失败或在没有aria2抱怨的情况下被破坏。
     * 添加BitTorrent Magnet URI时，
     * uris必须只有一个元素，它应该是BitTorrent Magnet URI。
     * options是一个结构，它的成员是选项名称和值的对。
     * 请参阅 下面的选项以获取更多详
     * 如果给定位置，则它必须是从0开始的整数。
     * 新的下载将插入 到等待队列中的位置。
     * 如果位置被省略或 位置如果大于队列的当前大小，则新下载将附加到队列的末尾。
     * 此方法返回新注册的下载的GID。
     * @param param
     * @return
     */
    public String addUrl(Param param){
        String post = post(Methods.ADD_URI, param);
        return post;
    }

    public String tellStatus(Param param){
        String post = post(Methods.TELL_STATUS, param);
        return post;
    }


    private String post(Methods methods , Param param){
        param.setMethod(methods.getValue());
        Map map = JSONUtil.objToMap(param);
        return httpClient.doPost(baseUrl, map);
    }

    public static void main(String[] args) {
        String a = "http://localhost:6800/jsonrpc";
        HttpClient httpClient = new HttpClient();
        Map map = new HashMap();
        map.put("jsonrpc","2.0");
        map.put("id","qwe");
        map.put("method","aria2.addUri");
        map.put("jsonrpc","2.0");
        httpClient.doPost(a, map);
    }
}
