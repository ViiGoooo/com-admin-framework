package com.admin.framework.component.logistics;

import com.admin.framework.component.annotations.NotNull;
import lombok.Data;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:20 2019\9\19 0019
 */
@Data
public class LogisticsRequest {
    /**
     * 	String	是		我方分配给贵司的的公司编号, 请参考邮件《快递100-企业版快递查询接口（API）——授权key及相关工具》
     */
    @NotNull
    private String customer;

    /**
     * 	Object	是		由其他字段拼接
     */
    @NotNull
    private Param paramEntity;
    private String param;


    /**
     * 分配的key
     */
    @NotNull
    private String key;

    /**
     * 	String	是		签名， 用于验证身份， 按param + key + customer 的顺序进行MD5加密（注意加密后字符串一定要转大写）， 不需要加上“+”号， 如{"com": "yuantong", "num": "500306190180", "from": "广东省深圳市", "to": "北京市朝阳区"}xxxxxxxxxxxxyyyyyyyyyyy yyyyyyyyyyyyyyyyyyyyy
     */
    private String sign;

    @Data
    public static class Param{
        /**
         * 	string	是	yuantong	查询的快递公司的编码， 一律用小写字母
         */
        @NotNull
        private String com;
        /**
         * 	string	是	12345678	查询的快递单号， 单号的最大长度是32个字符
         */
        @NotNull
        private String num;
        /**
         * 	string	否	13888888888	寄件人或收件人手机号（顺丰单号必填）
         */
        private String phone = "";
        /**
         * 	string	否	广东深圳	出发地城市
         */
        private String from = "";
        /**
         * 	string	否	北京朝阳	目的地城市，到达目的地后会加大监控频率
         */
        private String to = "";

        /**
         * 	int	否	1	添加此字段表示开通行政区域解析功能
         */
        private Integer resultv2 = 1;
    }
}
