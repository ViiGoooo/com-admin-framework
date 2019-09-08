package com.admin.framework.wechat.contanst;

import com.admin.framework.component.utils.StringUtil;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:18 2019\8\15 0015
 */
public enum WxMessageTypeEnum {

    TEXT(1,"text","文本"),
    IMAGE(2,"image","图片"),
    VOICE(3,"voice","声音"),
    VIDEO(4,"video","视频"),
    MUSIC(5,"music","音乐"),
    NEWS(6,"news","图文"),
    EVENT(6,"event","事件"),
    ;
    /**
     * @param key
     * @return
     */
    public static WxMessageTypeEnum getByKey(Integer key){
        if(key == null){
            return null;
        }
        for(WxMessageTypeEnum type:values()){
            if(type.getKey().equals(key)){
                return type;
            }
        }
        return null;
    }
    /**
     * @param value
     * @return
     */
    public static WxMessageTypeEnum getByValue(String value){
        if(StringUtil.isEmpty(value)){
            return null;
        }
        for(WxMessageTypeEnum type:values()){
            if(type.getValue().equals(value.toLowerCase())){
                return type;
            }
        }
        return null;
    }

    WxMessageTypeEnum(Integer key, String value, String  desc){
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
