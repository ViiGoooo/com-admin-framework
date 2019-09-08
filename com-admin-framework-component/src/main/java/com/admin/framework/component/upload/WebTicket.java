package com.admin.framework.component.upload;

import lombok.Data;

/**
 * web直传需要的信息
 * @Author zsw
 * @Description
 * @Date Create in 11:27 2019\8\30 0030
 */
@Data
public class WebTicket {
    private String tmpSecretId;
    private String tmpSecretKey;
    private String sessionToken;
    private String expiration;
    private Long startTime;
    private Long expiredTime;

}
