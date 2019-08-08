package com.admin.framework.component.http;


import com.admin.framework.component.utils.ArrayUtil;
import com.admin.framework.component.utils.IOUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 *
 * @author ZSW
 * @date 2019/8/8
 */
public class HttpClient {

    private final String HTTPS_PROTOCOL = "https";
    private final String HTTP_PROTOCOL = "http";
    private SSLKey sslKey;
    public HttpClient(){}
    public HttpClient(SSLKey sslKey){
        this.sslKey = sslKey;
    }

    /**
    * 向指定 URL 发送请求
    * @param url 发送请求的 URL
    * @param param 请求参数，请求参数应该是 json 的形式。
    *  @return 所代表远程资源的响应结果
    */
    public HttpResponse request(String url, String... param) throws HttpException {
        HttpURLConnection conn = getConnection(url);
        try {
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("content-Type", "application/json");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            if(!ArrayUtil.isEmpty(param)){
                out.print(param[0]);
            }
            // flush输出流的缓冲
            out.flush();
            return getResponse(conn);
        } catch (IOException e) {
            e.printStackTrace();
            throw new HttpException("发送请求失败",e);
        }
    }

    /**
     * 获取链接
     * @param url
     * @return
     * @throws HttpException
     */
    private HttpURLConnection getConnection(String url) throws HttpException {
        try {
            URL realUrl = new URL(url);
            if (url.startsWith(HTTPS_PROTOCOL)) {
                // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
                TrustManager[] tm = {new SSLTrustManager(sslKey)};
                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, tm, new java.security.SecureRandom());
                // 从上述SSLContext对象中得到SSLSocketFactory对象  
                SSLSocketFactory ssf = sslContext.getSocketFactory();

                // 打开和URL之间的连接

                HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
                conn.setSSLSocketFactory(ssf);
                return conn;
            } else {
                return (HttpsURLConnection) realUrl.openConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpException("获取http链接失败", e);
        }
    }


        /**
     * 获取响应数据
     * @param conn
     * @return
     * @throws IOException
     */
    private HttpResponse getResponse(HttpURLConnection conn) throws IOException {
        int responseCode = conn.getResponseCode();
        // 定义BufferedReader输入流来读取URL的响应
        InputStream inputStream = conn.getInputStream();
        HttpResponse response = new HttpResponse();
        response.setCode(responseCode);
        response.setInputStream(inputStream);
        String body = IOUtil.inputToString(inputStream);
        response.setBody(body);
        Map<String, List<String>> header = conn.getHeaderFields();
        response.setHeaders(header);
        return response;
    }

}
