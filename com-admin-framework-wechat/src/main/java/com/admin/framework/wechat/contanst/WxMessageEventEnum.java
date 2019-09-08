package com.admin.framework.wechat.contanst;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:42 2019\8\15 0015
 */
public enum WxMessageEventEnum {

    CLICK(1,"CLICK","菜单点击事件"),
//    VIEW(1,"VIEW","已关注"),
    SUBSCRIBE(2,"subscribe","关注事件"),
    UNSUBSCRIBE(3,"unsubscribe","取消关注"),
    SCAN(4,"SCAN","扫码"),
    ;
    /**
     * @param key
     * @return
     */
    public static WxMessageEventEnum getByKey(Integer key){
        if(key == null){
            return null;
        }
        for(WxMessageEventEnum type:values()){
            if(type.getKey().equals(key)){
                return type;
            }
        }
        return null;
    }

    WxMessageEventEnum(Integer key, String value, String  desc){
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
