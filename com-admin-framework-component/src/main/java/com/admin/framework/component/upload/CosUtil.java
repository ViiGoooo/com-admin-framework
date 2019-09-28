package com.admin.framework.component.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.admin.framework.component.utils.ImageUtil;
import com.admin.framework.component.utils.ListUtil;
import com.admin.framework.component.utils.NotNullVerifyUtil;
import com.admin.framework.component.utils.StringUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.tencent.cloud.CosStsClient;

/**
 * 腾讯云cos上传
 * @Author zsw
 * @Description
 * @Date Create in 11:19 2019\8\16 0016
 */
public class CosUtil {

    private COSClient cosClient;
    private UploadConfig uploadConfig;
    private CosUtil(){
    }

    private CosUtil(UploadConfig uploadConfig) {
        COSCredentials cred = new BasicCOSCredentials(uploadConfig.getSecretId(),uploadConfig.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(uploadConfig.getRegion()));
        this.cosClient = new COSClient(cred, clientConfig);
        this.uploadConfig = uploadConfig;
    }

    private static CosUtil cosUtil;

    /**
     * 单例工厂初始化
     * @return
     * @throws UploadException
     */
    public static CosUtil getInstance(UploadConfig uploadConfig) throws UploadException {
        List<String> verify = NotNullVerifyUtil.verify(uploadConfig);
        if(!ListUtil.isEmpty(verify)){
            throw new UploadException(verify.get(0));
        }
        if(cosUtil == null){
            cosUtil = new CosUtil(uploadConfig);
        }
        return cosUtil;
    }

    /**
     * 上传
     * @param key
     * @param input
     */
    public void put(String key, File input) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(uploadConfig.getBucketName(), key, input);
        cosClient.putObject(putObjectRequest);
    }

    /**
     * 上传
     * @param key
     * @param input
     */
    public void put(String key, InputStream input) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(uploadConfig.getBucketName(), key, input, null);
        cosClient.putObject(putObjectRequest);
    }

    /**
     * 删除
     * @param key
     */
    public void delete(String key){
        COSObject cosObject = get(key);
        if(cosObject != null){
            cosClient.deleteObject(uploadConfig.getBucketName(), key);
        }
    }

    /**
     * 获取单个
     * @param key
     * @return
     */
    public COSObject get(String key){
        if(StringUtil.isEmpty(key)){
            return null;
        }
        try {
            return cosClient.getObject(uploadConfig.getBucketName(), key);
        }catch (Exception e){
            return null;
        }
    }



    /**
     * 删除文件夹
     * @param folderName
     */
    public void deleteFolder(String folderName){
        List<COSObjectSummary> cosObjectSummaries = listBuFolder(folderName);
        for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
            String key = cosObjectSummary.getKey();
            long fileSize = cosObjectSummary.getSize();
            String eTag = cosObjectSummary.getETag();
            Date lastModified = cosObjectSummary.getLastModified();
            String storageClass = cosObjectSummary.getStorageClass();
            cosClient.deleteObject(uploadConfig.getBucketName(), key);
        }
    }

    /**
     * 获取文件下的所有文件
     * @param prefix
     */
    public List<COSObjectSummary> listBuFolder(String prefix){
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(uploadConfig.getBucketName());
        listObjectsRequest.setPrefix(prefix);
        listObjectsRequest.setMaxKeys(100);
        ObjectListing objectListing = cosClient.listObjects(listObjectsRequest);
        List<COSObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        return objectSummaries;
    }

    /**
     * 获取图片完整路径
     * @param key
     * @return
     */
    public String getVisitPath(String key){
        return uploadConfig.getDomainName() + key;
    }

    /**
     * 获取缩略图
     * @param key
     * @return
     */
    public String getThumbnailPath(String key){
        return uploadConfig.getDomainName() + key + "?imageMogr2/thumbnail/" + uploadConfig.getThumbnail();
    }

    /**
     * 获取缩略裁剪图
     * @param key
     * @return
     */
    public String getCropPath(String key, String width , String height){
        String str = uploadConfig.getDomainName() + key + "?imageMogr2/crop/%sx%s/gravity/center";
        return String.format(str,width,height);
    }


    public void getCredential(){
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        try {
            config.put("secretId", uploadConfig.getSecretId());
            config.put("secretKey", uploadConfig.getSecretKey());
            config.put("durationSeconds", 1800);
            config.put("bucket", uploadConfig.getBucketName());
            config.put("region", uploadConfig.getRegion());
            config.put("allowPrefix", "*");
            String[] allowActions = new String[] {
                    // 简单上传
                    "name/cos:PutObject",
                    // 表单上传
                    "name/cos:PostObject",
                    // 分片上传： 初始化分片
                    "name/cos:InitiateMultipartUpload",
                    // 分片上传： 查询 bucket 中未完成分片上传的UploadId
                    "name/cos:ListMultipartUploads",
                    // 分片上传： 查询已上传的分片
                    "name/cos:ListParts",
                    // 分片上传： 上传分片块
                    "name/cos:UploadPart",
                    // 分片上传： 完成分片上传
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);

            CosStsClient.getCredential(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
