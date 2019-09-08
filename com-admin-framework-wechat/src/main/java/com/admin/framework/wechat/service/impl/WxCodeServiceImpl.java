package com.admin.framework.wechat.service.impl;

import com.admin.framework.component.http.HttpClient;
import com.admin.framework.component.http.HttpException;
import com.admin.framework.component.http.HttpResponse;
import com.admin.framework.component.utils.*;
import com.admin.framework.wechat.entity.MiniCode;
import com.admin.framework.wechat.entity.WxResult;
import com.admin.framework.wechat.exception.WxException;
import com.admin.framework.wechat.service.WxCodeService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * @Author zsw
 * @Description
 * @Date Create in 10:14 2019\8\9 0009
 */
public class WxCodeServiceImpl implements WxCodeService {

    /**
     * 生成带参小程序二维码
     * @param miniCode	参数
     */
    public BufferedImage getminiqrQr(MiniCode miniCode) throws WxException {
        List<String> verify = NotNullVerifyUtil.verify(miniCode);
        if(ListUtil.isNotEmpty(verify)){
            throw new WxException(verify);
        }
        try {
            HttpClient httpClient = new HttpClient();

            String url = String.format(mini_code_url,miniCode.getAccessToken());
            String param = JSONUtil.objToJsonStr(miniCode);
            HttpResponse response = httpClient.request(url, param);
            InputStream inputStream = response.getInputStream();

            ByteArrayOutputStream byteArray = IOUtil.cloneInputStream(inputStream);
            InputStream source = new ByteArrayInputStream(byteArray.toByteArray());
            InputStream operation = new ByteArrayInputStream(byteArray.toByteArray());
            BufferedImage read = ImageIO.read(source);
            if(read != null){
                return read;
            }
            String body = FileUtil.load(operation);
            WxResult error = JSONUtil.jsonToObj(body, WxResult.class);
            throw new WxException(error);
        }catch (HttpException e){
            e.printStackTrace();
            throw new WxException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new WxException(e.getMessage());
        }
    }

}
