package com.admin.framework.wechat;

import com.admin.framework.component.utils.JSONUtil;
import com.admin.framework.wechat.entity.MiniCode;
import com.admin.framework.wechat.entity.WxConfig;
import com.admin.framework.wechat.entity.WxToken;

/**
 * @Author zsw
 * @Description
 * @Date Create in 15:50 2019\8\8 0008
 */
public class Test {


    public static void main(String[] args){
//        int x = 0;
//        //24_hqhMl8gNAdHQ7zKQNPN7TAXyknCJL8EBH4McDzXQH4OdWSyiyP8SZoklIbUxAaPQ9tfo1mLsjTfsMMxJUyhaars9KIze3Qowcf-nP_kkUBcmYfUpng2Xv-ORtZalf-QgOPs6TtaaTk9wd24TABPaAFADES
//        try {
//            WxToken wxToken = AppAccessTokenService.getToken(new WxConfig("wx53b03c84d9aa1e79", "8f34b7737f154a7785e664340f644c12"));
//            BufferedImage image = WxMiniCodeService.getminiqrQr(new MiniCode(wxToken.getAccessToken(), "aa", "aa"));
//        }catch (WxException e){
//            e.printStackTrace();
//            e.getCode();
//        }

//        WxToken wxToken = new WxToken();
//        wxToken.setAccessToken("24_iww6s5jF_R-IS71XJHxIbniVGw4JXd-KBygEbeQFJGjx2CpPUlnUdpDN-wIJdIRMQK7cb7ODKdZ4pn5HA_ugxHqtz14fWszj59bNjpdjiTiR2p1joy4YByRv0Jkobq1lh1PGGk3RBWDjvMfvFFXgAHAXXP");
//        wxToken.setExpiresIn(7200);
//        wxToken.setLoadTime(System.currentTimeMillis());
//        String s = JSONUtil.objToJson(wxToken);
//        int x = 0;
        String a = "{\"scene\":\"c#10044\",\"path\":\"pages/mine/index\",\"width\":430,\"auto_color\":true,\"line_color\":null,\"hyaline\":false}";
        String token = "24_PBP1Dm7cBQydOMrPzQGkrbtBJ9TYTJwW1aoa_6Uz-2I410rQIUCEDuoMXeL4GQzoHZRvhR6ztDOa_oGZUwQkNRB4sQxVWx_mfLpYVY_QfNQunsq0pu9gq7Fcs86P4EH0Qd6s_pRBpR2BVKfNXXQbAAAEEX";

//        MiniCode miniCode = new MiniCode(token, "c#10044", "pages/mine/index");
//        miniCode.setWidth(430);
//        miniCode.setAutoColor(true);
//        String s = JSONUtil.objToJson(miniCode);
//        System.out.println(s);

        WxToken wxToken = new WxToken();
        wxToken.setAccessToken(token);
        wxToken.setExpiresIn(7200);
        wxToken.setLoadTime(System.currentTimeMillis());
        String s = JSONUtil.objToJsonStr(wxToken);
        System.out.println(s);

    }

}
