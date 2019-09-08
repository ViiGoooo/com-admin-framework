package com.admin.framework.wechat.contanst;

/**
 * @Author zsw
 * @Description
 * @Date Create in 19:38 2019\8\8 0008
 */
public enum SubscribeEnum {

    SUBSCRIBED(1,"subscribed","已关注"),
    UNSUBSCRIBE(2,"unSubscribe","未关注")
    ;
    /**
     * @param key
     * @return
     */
    public static SubscribeEnum getByKey(Integer key){
        if(key == null){
            return null;
        }
        for(SubscribeEnum type:values()){
            if(type.getKey().equals(key)){
                return type;
            }
        }
        return null;
    }

    SubscribeEnum(Integer key, String value, String  desc){
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
