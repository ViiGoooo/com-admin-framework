package com.admin.framework.wechat.contanst;

/**
 * @Author zsw
 * @Description
 * @Date Create in 13:56 2019\9\17 0017
 */
public enum WxMediaTypeEnum {

    /*
     分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     */

    IMAGE(1,"image","图片"),
    VOICE(2,"voice","语音"),
    VIDEO(3,"video","视频"),
    THUMB(4,"thumb","缩略图")
    ;
    /**
     * @param key
     * @return
     */
    public static WxMediaTypeEnum getByKey(Integer key){
        if(key == null){
            return null;
        }
        for(WxMediaTypeEnum type:values()){
            if(type.getKey().equals(key)){
                return type;
            }
        }
        return null;
    }

    WxMediaTypeEnum(Integer key, String value, String  desc){
        this.key = key;
        this.value = value;
        this.desc = desc;
    }


    private Integer key;
    private String value;
    private String desc;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
