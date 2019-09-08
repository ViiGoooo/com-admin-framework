package com.admin.framework.component.sms;

/**
 * @Author zsw
 * @Description
 * @Date Create in 19:38 2019\8\5 0005
 */
public enum SmsEnum {

    TENCENT(1,"tencent","腾讯"),
    ALIBABA(2,"Alibaba","阿里巴巴")
    ;


    SmsEnum(Integer key,String value,String desc){
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
