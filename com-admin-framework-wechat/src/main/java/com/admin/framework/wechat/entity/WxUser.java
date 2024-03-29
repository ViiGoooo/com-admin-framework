package com.admin.framework.wechat.entity;

import com.admin.framework.component.utils.DateUtil;
import com.admin.framework.component.utils.JSONUtil;
import com.admin.framework.component.utils.MapUtil;
import com.admin.framework.wechat.contanst.SubscribeEnum;
import com.admin.framework.wechat.contanst.SubscribeSceneEnum;
import com.admin.framework.wechat.contanst.WxSexEnum;
import com.admin.framework.wechat.exception.WxException;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @Author zsw
 * @Description
 * @Date Create in 19:26 2019\8\8 0008
 */
@Data
public class WxUser {
    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private SubscribeEnum subscribe;

    /**
     * 用户的标识，对当前公众号唯一
     */
    private String openid;
    /**
     * 用户的昵称
     */
    private String nickname;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private WxSexEnum sex;

    /**
     * 用户的语言，简体中文为zh_CN
     */
    private String language;

    /**
     * 	用户所在城市
     */
    private String city;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    private String headimgurl;

    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    private Date subscribeTime;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;

    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;

    /**
     * 用户所在的分组ID（兼容旧的用户分组接口）
     */
    private String groupid;

    /**
     * 用户被打上的标签ID列表
     */
    private String tagidList;

    /**
     * 返回用户关注的渠道来源，
     */
    private String qrScene;

    /**
     * 二维码扫码场景（开发者自定义）
     */
    private String qrSceneStr;

    /**
     * 二维码扫码场景描述（开发者自定义）
     */
    private SubscribeSceneEnum subscribeScene;

    /**
     * 构造微信对象
     * @param jsonStr
     * @return
     */
    public static WxUser build(String jsonStr) throws WxException {
        Map map = null;
        try {
             map = JSONUtil.jsonToObj(jsonStr, Map.class);
        }catch (Exception e){
            throw new WxException(e);
        }
        Integer subscribe = MapUtil.getInteger(map, "subscribe");
        String openid = MapUtil.getString(map, "openid");
        String nickname = MapUtil.getString(map, "nickname");
        Integer sex = MapUtil.getInteger(map, "sex");
        String city = MapUtil.getString(map, "city");
        String country = MapUtil.getString(map, "country");
        String province = MapUtil.getString(map, "province");
        String language = MapUtil.getString(map, "language");
        String headimgurl = MapUtil.getString(map, "headimgurl");
        Long subscribe_time = MapUtil.getLong(map, "subscribe_time");
        String unionid = MapUtil.getString(map, "unionid");
        String remark = MapUtil.getString(map, "remark");
        String groupid = MapUtil.getString(map, "groupid");
        String tagid_list = MapUtil.getString(map, "tagid_list");
        String subscribe_scene = MapUtil.getString(map, "subscribe_scene");
        String qr_scene = MapUtil.getString(map, "qr_scene");
        String qr_scene_str = MapUtil.getString(map, "qr_scene_str");

        WxUser wxUser = new WxUser();

        SubscribeEnum subscribeEnum = null;
        if(subscribe == null){
            subscribeEnum = SubscribeEnum.UNSUBSCRIBE;
        }else{
            subscribeEnum = subscribe.equals(0)?SubscribeEnum.UNSUBSCRIBE:SubscribeEnum.SUBSCRIBED;
        }
        wxUser.setSubscribe(subscribeEnum);
        wxUser.setOpenid(openid);
        wxUser.setNickname(nickname);
        wxUser.setSex(WxSexEnum.getByKey(sex));
        wxUser.setCity(city);
        wxUser.setCountry(country);
        wxUser.setProvince(province);
        wxUser.setLanguage(language);
        wxUser.setHeadimgurl(headimgurl);
        wxUser.setSubscribeTime(subscribe_time != null ?new Date(subscribe_time):null);
        wxUser.setUnionid(unionid);
        wxUser.setRemark(remark);
        wxUser.setGroupid(groupid);
        wxUser.setTagidList(tagid_list);
        wxUser.setSubscribeScene(SubscribeSceneEnum.getByValue(subscribe_scene));
        wxUser.setQrScene(qr_scene);
        wxUser.setQrSceneStr(qr_scene_str);
        return wxUser;
    }

}
