package com.admin.framework.component.utils;

import java.io.*;

/**
 * Created by ZSW on 2019/8/8.
 */
public class IOUtil {

    /**
     * 输入流拷贝
     * @param input
     * @return
     * @throws IOException
     */
    public static InputStream cloneInputStream(InputStream input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = input.read(buffer)) > -1 ) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        return new ByteArrayInputStream(baos.toByteArray());
    }

    /**
     * 输入流转string
     * @param is
     * @return
     * @throws IOException
     */
    public static String inputToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        while(br.read() != -1){
            sb.append(br.readLine());
        }
        return new String(sb.toString().getBytes("GBK"), "ISO-8859-1");
    }

}
