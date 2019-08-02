package com.admin.framework.component.aria2c.contanst;

/**
 * Created by ZSW on 2019/2/25.
 */
public enum Methods {


    ADD_URI(1,"aria2.addUri"),
    ADD_TORRENT(1,"aria2.addTorrent"),
    ADD_METALINK(1,"aria2.addMetalink"),
    REMOVE(1,"aria2.remove"),
    TELL_STATUS(1,"aria2.tellStatus"),
    ;

    Methods(Integer key,String value){
        this.key = key;
        this.value = value;
    }

    private Integer key;
    private String value;

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
}
