package com.admin.framework.component.logistics;

import com.admin.framework.component.http.HttpClient;
import com.admin.framework.component.http.HttpException;
import com.admin.framework.component.http.HttpResponse;
import com.admin.framework.component.utils.*;

import java.util.List;
import java.util.Map;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:28 2019\9\19 0019
 */
public class LogisticsClient {

    private LogisticsRequest request;
    private final String URL = "http://poll.kuaidi100.com/poll/query.do";

    /**
     * 构造器初始化
     * @param request
     * @throws LogisticsException
     */
    public LogisticsClient(LogisticsRequest request) throws LogisticsException {
        LogisticsRequest.Param param = request.getParamEntity();
        String customer = request.getCustomer();
        String key = request.getKey();
        String paramStr = JSONUtil.objToJsonStr(param);
        String sign = MD5Util.encode(paramStr + key + customer);
        request.setSign(sign);
        request.setParam(paramStr);
        List<String> verify = NotNullVerifyUtil.verify(request);
        if(!ListUtil.isEmpty(verify)){
            throw new LogisticsException(verify.toString());
        }
        List<String> paramVerify = NotNullVerifyUtil.verify(param);
        if(!ListUtil.isEmpty(paramVerify)){
            throw new LogisticsException(paramVerify.toString());
        }
        request.setKey(null);
        request.setParamEntity(null);

        this.request = request;
    }

    /**
     * 获取物流信息
     * @return
     */
    public LogisticsResponse query() throws LogisticsException {
        Map param = ReflectUtil.beanToMap(request);
        HttpClient httpClient = new HttpClient();
        try {
            HttpResponse post = httpClient.post(URL, param);
            String body = post.getBody();
            LogisticsResponse response = JSONUtil.jsonToObj(body, LogisticsResponse.class);
            return response;
        } catch (HttpException e) {
            e.printStackTrace();
            throw new LogisticsException(e);
        }
    }


    public static void main(String[] args) {
        try {

            LogisticsRequest request = new LogisticsRequest();
            request.setCustomer("B438AF676C68D6E4FDAA127D6D305188");
            request.setKey("ScfIBKeo3571");
            LogisticsRequest.Param param = new LogisticsRequest.Param();
            param.setCom("huitongkuaidi");
            param.setNum("71646434988748");
            request.setParamEntity(param);

            LogisticsClient client = new LogisticsClient(request);
            LogisticsResponse query = client.query();
            System.out.println(JSONUtil.objToJsonStr(query));
        } catch (LogisticsException e) {
            e.printStackTrace();
        }
    }


}
