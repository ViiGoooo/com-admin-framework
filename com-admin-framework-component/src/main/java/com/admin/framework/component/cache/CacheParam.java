package com.admin.framework.component.cache;

import com.admin.framework.component.annotations.NotNull;
import lombok.Data;

/**
 *
 * @author ZSW
 * @date 2019/1/17
 */
@Data
public class CacheParam {
    @NotNull(message = "主机地址不能为空")
    private String host;

    @NotNull(message = "端口不能为空")
    private Integer port;
    private String auth;

    /**
     * 最大链接
     */
    private Integer maxTotal;

    /**
        最小空余
     */
    private Integer minIdle;

    /**
     * 最大空余
     */
    private Integer maxIdle;

    /**
     * 指定的库
     */
    @NotNull(message = "请设置指定的库")
    private Integer db;

}
