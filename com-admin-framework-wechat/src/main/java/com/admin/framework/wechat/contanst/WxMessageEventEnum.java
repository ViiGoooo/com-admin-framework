package com.admin.framework.wechat.contanst;

import com.admin.framework.component.utils.StringUtil;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:42 2019\8\15 0015
 */
public enum WxMessageEventEnum {

    CLICK(1,"CLICK","点击菜单拉取消息时的事件推送"),
    VIEW(1,"VIEW","点击菜单跳转链接时的事件推送"),
    VIEW_MINIPROGRAM(1,"view_miniprogram","点击菜单跳转小程序的事件推送"),
    SUBSCRIBE(2,"subscribe","关注事件"),
    UNSUBSCRIBE(3,"unsubscribe","取消关注"),
    SCAN(4,"scan","扫码"),
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

    /**
     * @param value
     * @return
     */
    public static WxMessageEventEnum getByValue(String value){
        if(StringUtil.isEmpty(value)){
            return null;
        }
        for(WxMessageEventEnum type:values()){
            if(type.getValue().equals(value)){
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
