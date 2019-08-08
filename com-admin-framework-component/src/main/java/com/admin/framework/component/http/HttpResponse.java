package com.admin.framework.component.http;

import com.admin.framework.component.utils.IOUtil;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 *
 * @author ZSW
 * @date 2019/8/8
 */
@Data
public class HttpResponse {

    private Integer code;
    private String body;
    private InputStream inputStream;
    private Map headers;





}
