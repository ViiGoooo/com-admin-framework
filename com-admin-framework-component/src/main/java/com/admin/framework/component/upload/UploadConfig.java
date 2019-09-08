package com.admin.framework.component.upload;

import lombok.Data;

/**
 * @Author zsw
 * @Description
 * @Date Create in 10:41 2019\8\18 0018
 */
@Data
public class UploadConfig {


    /**
     * 秘钥id
     */
    private String secretId;

    /**
     * 秘钥
     */
    private String secretKey;

    /**
     * 区域
     */
    private String region;

    /**
     * 域名
     */
    private String domainName;

    /**
     * 存储桶
     */
    private String bucketName;

    /**
     * 文件处理策略
     */
    private String thumbnail;

}
