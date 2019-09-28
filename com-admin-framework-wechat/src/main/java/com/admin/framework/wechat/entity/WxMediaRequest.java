package com.admin.framework.wechat.entity;

import com.admin.framework.component.annotations.NotNull;
import com.admin.framework.wechat.contanst.WxMediaTypeEnum;
import lombok.Data;

import java.io.InputStream;

/**
 * @Author zsw
 * @Description
 * @Date Create in 14:05 2019\9\17 0017
 */
@Data
public class WxMediaRequest {
    @NotNull(message = "accessToken不能为空")
    private String accessToken;
    @NotNull(message = "文件类型不能为空")
    private WxMediaTypeEnum type;
    @NotNull(message = "文件流不能为空")
    private InputStream inputStream;
    @NotNull(message = "文件名不能为空")
    private String fileName;

}
