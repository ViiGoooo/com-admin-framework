package com.admin.framework.component.aria2c.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by ZSW on 2019/2/25.
 */
@Data
public class Param {

    private String jsonrpc;
    private String id;
    private String method;
    private List<List<String>> params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<List<String>> getParams() {
        return params;
    }

    public void setParams(List<List<String>> params) {
        this.params = params;
    }
}
