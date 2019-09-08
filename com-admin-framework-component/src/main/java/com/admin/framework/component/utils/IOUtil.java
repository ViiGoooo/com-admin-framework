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
    public static ByteArrayOutputStream cloneInputStream(InputStream input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = input.read(buffer)) > -1 ) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        return baos;
    }

    /**
     * 输入流转string
     * @param is
     * @return
     * @throws IOException
     */
    public static String inputToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
