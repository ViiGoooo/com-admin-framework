package com.admin.framework.component.http;

import lombok.Data;

/**
 *
 * @author ZSW
 * @date 2019/8/8
 */
@Data
public class SSLKey {
    /**
     * 证书所在的位置（绝对路径）
     */
    private String store;
    private String pass;
}
