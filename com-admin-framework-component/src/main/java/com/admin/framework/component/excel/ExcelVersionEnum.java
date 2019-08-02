package com.admin.framework.component.excel;

/**
 *
 * @author ZSW
 * @date 2019/1/22
 */
public enum  ExcelVersionEnum {

    MICROSOFT_OFFICE_2007(1,"xlsx"),
    MICROSOFT_OFFICE_2003(2,"xls")
            ;

    ExcelVersionEnum(Integer key,String suffix){
        this.key = key;
        this.suffix = suffix;
    }

    private Integer key;
    private String suffix;


    public Integer getKey() {
        return key;
    }

    public ExcelVersionEnum setKey(Integer key) {
        this.key = key;
        return this;
    }

    public String getSuffix() {
        return suffix;
    }

    public ExcelVersionEnum setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

}
