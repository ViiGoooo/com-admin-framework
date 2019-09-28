package com.admin.framework.component.http;


import com.admin.framework.component.utils.ArrayUtil;
import com.admin.framework.component.utils.IOUtil;
import com.admin.framework.component.utils.JSONUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
    private final String DEFAULT_CHARSET = "UTF-8";
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
    public HttpResponse post(String url, String... param) throws HttpException {
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
    /*
param=%7B%22com%22%3A%22huitongkuaidi%22%2C%22num%22%3A%2271646434988748%22%2C%22phone%22%3A%22%22%2C%22from%22%3A%22%22%2C%22to%22%3A%22%22%2C%22resultv2%22%3A1%7D&sign=BA65D3B9B522F535501F727E80C7ED10&customer=B438AF676C68D6E4FDAA127D6D305188
param=%7B%22com%22%3A%22huitongkuaidi%22%2C%22num%22%3A%2271646434988748%22%7D&sign=1839B5D4BC5CDE2159276BF7BE276199&customer=B438AF676C68D6E4FDAA127D6D305188
     */

    public HttpResponse post(String url, Map<Object,Object> paramMap) throws HttpException {
        HttpURLConnection conn = getConnection(url);
        try {
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("content-Type", "application/x-www-form-urlencoded");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            String param = "";
            for (Map.Entry<Object,Object> entry:paramMap.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                if(v != null && !"".equals(v)){
                    String key = URLEncoder.encode(k.toString(),DEFAULT_CHARSET);
                    String value = URLEncoder.encode(v.toString(),DEFAULT_CHARSET);
                    param += key + "=" + value + "&";
                }
            }
            param = param.substring(0,param.lastIndexOf("&"));

            // 获取URLConnection对象对应的输出流
            OutputStream outputStream = conn.getOutputStream();
            // 发送请求参数
            byte[] bytes = param.getBytes();
            outputStream.write(bytes);
            // flush输出流的缓冲
            outputStream.flush();
            return getResponse(conn);
        } catch (IOException e) {
            e.printStackTrace();
            throw new HttpException("发送请求失败",e);
        }
    }



    /**
     * 发送form表单
     * @param url
     * @param inputStream
     * @param fileName
     * @return
     * @throws HttpException
     */
    public HttpResponse postForm(String url, InputStream inputStream,String fileName) throws HttpException{

        try {
            HttpURLConnection conn = getConnection(url);

            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Cache-Control", "no-cache");
            String boundary = "-----------------------------" + System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            OutputStream output = conn.getOutputStream();
            output.write(("--" + boundary + "\r\n").getBytes());
            output.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", fileName).getBytes());
            output.write("Content-Type: video/mp4 \r\n\r\n".getBytes());

            byte[] data = new byte[1024];
            int len = 0;

            while ((len = inputStream.read(data)) > -1) {
                output.write(data, 0, len);
            }

            /*对类型为video的素材进行特殊处理*/
//            if ("video".equals(type)) {
//                output.write(("--" + boundary + "\r\n").getBytes());
//                output.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes());
//                output.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}", title, introduction).getBytes());
//            }

            output.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
            output.flush();
            output.close();
            inputStream.close();


            return getResponse(conn);
        }catch (IOException e){
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
                return (HttpURLConnection) realUrl.openConnection();
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
        ByteArrayOutputStream bos = IOUtil.cloneInputStream(inputStream);
        InputStream stream1 = new ByteArrayInputStream(bos.toByteArray());
        InputStream stream2 = new ByteArrayInputStream(bos.toByteArray());
        response.setInputStream(stream1);
        String body = IOUtil.inputToString(stream2);
        response.setBody(body);
        Map<String, List<String>> header = conn.getHeaderFields();
        response.setHeaders(header);
        return response;
    }


    public static void main(String[] args) throws FileNotFoundException, HttpException {
        String token = "25_RrvgzyoQFsD78LqeQLSfeFzR_R530h6rKnbPSIlQ5ghbDH_jRMa0A1iNm_8uTL7MYZb3ZdTqfHQNn6AKS_kb9pwWZUwY-AHO525tMhBwJIL7SGV_l--sDZUP5MO6w8RnoObUq_H7m-z832W0XOUeADAIZF";
        String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="+token+"&type=image";
        InputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\c\\1.png"));
        HttpClient client = new HttpClient();
        HttpResponse response = client.postForm(url, fileInputStream, "a.png");
        String body = response.getBody();
        System.out.println(body);
    }

}
