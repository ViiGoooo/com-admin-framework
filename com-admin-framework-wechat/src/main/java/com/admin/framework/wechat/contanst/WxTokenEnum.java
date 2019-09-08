package com.admin.framework.wechat.contanst;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:30 2019\8\8 0008
 */
public enum WxTokenEnum {


    APP(1,"app","普通accessToken获取"),
    WEB(2,"web","网页授权accessToken获取"),
    ;
    /**
     * @param key
     * @return
     */
    public static WxTokenEnum getByKey(Integer key){
        if(key == null){
            return null;
        }
        for(WxTokenEnum type:values()){
            if(type.getKey().equals(key)){
                return type;
            }
        }
        return null;
    }

    WxTokenEnum(Integer key, String value, String  desc){
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
