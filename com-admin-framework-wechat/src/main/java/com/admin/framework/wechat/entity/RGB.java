package com.admin.framework.wechat.entity;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

/**
 * 色值
 * @Author zsw
 * @Description
 * @Date Create in 13:59 2019\8\8 0008
 */
@Data
public class RGB {

    private String r;
    private String g;
    private String b;

    public RGB(){}

    public RGB(String r,String g,String b){
        this.r = r;
        this.g = g;
        this.b = b;
    }


}
