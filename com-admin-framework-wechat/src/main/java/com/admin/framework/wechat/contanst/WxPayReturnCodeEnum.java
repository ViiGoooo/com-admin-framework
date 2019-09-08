package com.admin.framework.wechat.contanst;

/**
 * @Author zsw
 * @Description
 * @Date Create in 20:28 2019\8\14 0014
 */
public enum WxPayReturnCodeEnum {

    SUCCESS(1,"SUCCESS","成功"),
    FAIL(2,"FAIL","失败"),
    ;
    /**
     * @param key
     * @return
     */
    public static WxPayReturnCodeEnum getByKey(Integer key){
        if(key == null){
            return null;
        }
        for(WxPayReturnCodeEnum type:values()){
            if(type.getKey().equals(key)){
                return type;
            }
        }
        return null;
    }

    WxPayReturnCodeEnum(Integer key, String value, String  desc){
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
