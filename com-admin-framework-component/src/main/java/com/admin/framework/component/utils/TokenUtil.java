package com.admin.framework.component.utils;

import java.util.UUID;

/**
 * @Author zsw
 * @Description
 * @Date Create in 16:03 2019\8\12 0012
 */
public class TokenUtil {

    /**
     * 生成唯一token
     * @return
     */
    public static String genToken(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
