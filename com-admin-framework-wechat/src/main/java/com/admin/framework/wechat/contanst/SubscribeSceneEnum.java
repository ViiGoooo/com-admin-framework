package com.admin.framework.wechat.contanst;

import com.admin.framework.component.utils.StringUtil;

/**
 * @Author zsw
 * @Description
 * @Date Create in 19:34 2019\8\8 0008
 */
public enum SubscribeSceneEnum {
    /**
     * ADD_SCENE_SEARCH 公众号搜索，
     * ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，
     * ADD_SCENE_PROFILE_CARD 名片分享，
     * ADD_SCENE_QR_CODE 扫描二维码，
     * ADD_SCENEPROFILE_LINK 图文页内名称点击，
     * ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，
     * ADD_SCENE_PAID 支付后关注，
     * ADD_SCENE_OTHERS 其他
      */
    ADD_SCENE_SEARCH(0,"ADD_SCENE_SEARCH","公众号搜索"),
    ADD_SCENE_ACCOUNT_MIGRATION(0,"ADD_SCENE_ACCOUNT_MIGRATION","公众号迁移"),
    ADD_SCENE_PROFILE_CARD(0,"ADD_SCENE_PROFILE_CARD","名片分享"),
    ADD_SCENE_QR_CODE(0,"ADD_SCENE_QR_CODE","扫描二维码"),
    ADD_SCENEPROFILE_LINK(0,"ADD_SCENEPROFILE_LINK","图文页内名称点击"),
    ADD_SCENE_PROFILE_ITEM(0,"ADD_SCENE_PROFILE_ITEM","图文页右上角菜单"),
    ADD_SCENE_PAID(0,"ADD_SCENE_PAID","支付后关注"),
    ADD_SCENE_OTHERS(0,"ADD_SCENE_OTHERS","其他"),
    ;

    public static SubscribeSceneEnum getByValue(String value){
        if(StringUtil.isEmpty(value)){
            return null;
        }
        for(SubscribeSceneEnum type:values()){
            if(type.getValue().equals(value)){
                return type;
            }
        }
        return null;
    }

    /**
     * @param key
     * @return
     */
    public static SubscribeSceneEnum getByKey(Integer key){
        if(key == null){
            return null;
        }
        for(SubscribeSceneEnum type:values()){
            if(type.getKey().equals(key)){
                return type;
            }
        }
        return null;
    }

    SubscribeSceneEnum(Integer key, String value, String  desc){
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
