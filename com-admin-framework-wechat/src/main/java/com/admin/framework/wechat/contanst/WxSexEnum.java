package com.admin.framework.wechat.contanst;

/**
 * @Author zsw
 * @Description
 * @Date Create in 19:31 2019\8\8 0008
 */
public enum WxSexEnum {


    unknown(0,"unknown","未知"),
    male(1,"male","男性"),
    female(2,"female","女性")
    ;
    /**
     * @param key
     * @return
     */
    public static WxSexEnum getByKey(Integer key){
        if(key == null){
            return null;
        }
        for(WxSexEnum type:values()){
            if(type.getKey().equals(key)){
                return type;
            }
        }
        return null;
    }

    WxSexEnum(Integer key, String value, String  desc){
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
