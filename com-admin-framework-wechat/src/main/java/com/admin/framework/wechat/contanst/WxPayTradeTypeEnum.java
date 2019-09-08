package com.admin.framework.wechat.contanst;

/**
 * @Author zsw
 * @Description
 * @Date Create in 17:59 2019\8\14 0014
 */
public enum WxPayTradeTypeEnum {
    /**
     * JSAPI -JSAPI支付
     *
     * NATIVE -Native支付
     *
     * APP -APP支付
     */
    JSAPI(1,"JSAPI","JSAPI支付（或小程序支付）"),
    NATIVE(2,"NATIVE","NATIVE--Native支付"),
    APP(3,"APP","APP--app支付")
    ;
    /**
     * @param key
     * @return
     */
    public static WxPayTradeTypeEnum getByKey(Integer key){
        if(key == null){
            return null;
        }
        for(WxPayTradeTypeEnum type:values()){
            if(type.getKey().equals(key)){
                return type;
            }
        }
        return null;
    }

    WxPayTradeTypeEnum(Integer key, String value, String  desc){
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
