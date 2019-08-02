package com.admin.framework.component.aria2c;

import com.admin.framework.component.aria2c.action.RPC;
import com.admin.framework.component.aria2c.entity.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZSW on 2019/2/25.
 */
public class Main {

    public static void main(String[] args) {

        String a = "http://localhost:6800/jsonrpc";

        List<List<String>> list = new ArrayList<>();
        List<String> l = new ArrayList();
        l.add("magnet:?xt=urn:btih:5d78d13d072defffc1a5585e74ae700bcaa19178");

        list.add(l);
        RPC rpc = new RPC(a);
        Param param = new Param();
        param.setId("aaa");
        param.setJsonrpc("2.0");
        param.setParams(list);
        String s = rpc.addUrl(param);
        System.out.print(s);
    }

}
