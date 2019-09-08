package com.admin.framework.wechat.contanst;

/**
 * @Author zsw
 * @Description
 * @Date Create in 10:00 2019\8\15 0015
 */
public enum  WxButtonTypeEnum {
    VIEW(1,"view","网页类型"),
    CLICK(0,"click","点击类型"),
    MINI_PROGRAM(0,"miniprogram","小程序类型")
    ;
    /**
     * @param key
     * @return
     */
    public static WxButtonTypeEnum getByKey(Integer key){
        if(key == null){
            return null;
        }
        for(WxButtonTypeEnum type:values()){
            if(type.getKey().equals(key)){
                return type;
            }
        }
        return null;
    }

    WxButtonTypeEnum(Integer key, String value, String  desc){
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
