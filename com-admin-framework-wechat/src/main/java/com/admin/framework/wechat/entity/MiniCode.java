package com.admin.framework.wechat.entity;

import com.admin.framework.component.annotations.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NonNull;

/**
 * @Author zsw
 * @Description
 * @Date Create in 13:56 2019\8\8 0008
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MiniCode {
    @NotNull(message = "accessToken不能为空")
    @JsonIgnore
    private String accessToken;

    /**
     * 最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
     */
    @NotNull(message = "请指定场景值,最大字符数不能超过32位",maxLength = 32)
    private String scene;

    /**
     * 必须是已经发布的小程序存在的页面（否则报错），例如 pages/index/index, 根路径前不要填加 /,不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面
     */
    @NotNull
    private String page;
    private Integer width;
    private Boolean autoColor;
//    private String lineColor;
//    private Boolean isHyaline;


    public MiniCode(){}

    public MiniCode(String accessToken, String scene, String page){
        this.accessToken = accessToken;
        this.scene = scene;
        this.page = page;
    }

}
