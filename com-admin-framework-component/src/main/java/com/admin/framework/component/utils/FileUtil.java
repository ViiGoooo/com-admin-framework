package com.admin.framework.component.utils;



import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件操作工具
 * @author ZSW
 * @date 2018/8/11
 */
public class FileUtil {
    /**
     * 默认字符
     */
    private static final String DEFAULT_ENCODING = "UTF-8";


    private static final String WINDOWS_FILE_SEPARATE = "\\";
    private static final String LINUX_FILE_SEPARATE = "/";

    /**
     * 保存文件（流的方式）
     * @param inputStream
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String save(InputStream inputStream,String fileName) throws IOException {
        return save(inputStream,getFile(fileName));
    }

    /**
     * 保存文件（bufferedImage）
     * @param bufferedImage
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String save(BufferedImage bufferedImage, String fileName) throws IOException {
        InputStream inputStream = ImageUtil.BufferedToInputstream(bufferedImage);
        return save(inputStream,getFile(fileName));
    }


    /**
     * 保存文件（流的方式）
     * @param inputStream
     * @param file
     * @return
     * @throws IOException
     */
    public static String save(InputStream inputStream,File file){
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            byte[] in2b = swapStream.toByteArray();
            return save(in2b,file);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 保存文件（字节数组的方式）
     * @param bytes
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String save(byte[] bytes,String filePath){
        return save(bytes,getFile(filePath));
    }
    /**
     * 保存文件（字节数组的方式）
     * @param bytes
     * @param file
     * @return
     * @throws IOException
     */
    public static String save(byte[] bytes,File file) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(bytes);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
            }
            is.close();
            outputStream.close();
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存文件（字符串）
     * @param contents
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String save(String contents,String filePath){
        return save(contents.getBytes(),getFile(filePath));
    }

    /**
     * 保存文件（字符串）
     * @param contents
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String save(List<String> contents, String filePath){
        String content = "";
        for(String str:contents){
            content += str + "\n";
        }
        return save(content,getFile(filePath));
    }
    /**
     * 保存文件（字符串）
     * @param contents
     * @param file
     * @return
     * @throws IOException
     */
    public static String save(String contents,File file){
        return save(contents.getBytes(),file);
    }


    /**
     * 获取文件
     * @param filePath
     * @return
     */
    public static File getFile(String filePath){
        String dir = "";
        String name = "";
        if(filePath.contains(LINUX_FILE_SEPARATE)){
            dir = filePath.substring(0,filePath.lastIndexOf(LINUX_FILE_SEPARATE));
            name = filePath.substring(filePath.lastIndexOf(LINUX_FILE_SEPARATE),filePath.length());
        }
        if(filePath.contains(WINDOWS_FILE_SEPARATE)){
            dir = filePath.substring(0,filePath.lastIndexOf(WINDOWS_FILE_SEPARATE));
            name = filePath.substring(filePath.lastIndexOf(WINDOWS_FILE_SEPARATE),filePath.length());
        }
        File dirFile = new File(dir);
        try {
            if(!dirFile.exists()){
                dirFile.mkdirs();
            }
            File file = new File(dir+name);
            if(!file.exists()){
                file.createNewFile();
            }
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 读取文件
     * @param filePath
     * @return
     */
    public static String load(String filePath){
        File file = new File(filePath);
        return load(file,DEFAULT_ENCODING);
    }
    /**
     * 读取文件
     * @param filePath
     * @return
     */
    public static String load(String filePath,String encoding){
        File file = new File(filePath);
        return load(file,encoding);
    }
    /**
     * 读取文件
     * @param file
     * @return
     */
    public static String load(File file){
        return load(file,DEFAULT_ENCODING);
    }

    /**
     * 读取文件
     * @param file
     * @return
     */
    public static String load(File file,String encoding){
        if(!file.exists()){
            throw new RuntimeException("文件不存在");
        }
        try {
            Long fileLength = file.length();
            byte[] fileContent = new byte[fileLength.intValue()];
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
            return new String(fileContent,encoding);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 从inputStream中获取
     * @param inputStream
     * @return
     */
    public static String load(InputStream inputStream){
        return load(inputStream,DEFAULT_ENCODING);
    }

    /**
     * 从inputStream中获取
     * @param inputStream
     * @param encoding
     * @return
     */
    public static String load(InputStream inputStream,String encoding){
        try {
            byte data[]=new byte[1024];
            inputStream.read(data);
            inputStream.close();
            return new String(data,encoding);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    /**
     * 通过文件路径获取w文件
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream getInputStreamByPath(String path) throws FileNotFoundException {
        InputStream inputStream = null;
        if(path.startsWith("http")){
            inputStream = getInputStreamFromWeb(path);
        }else{
            inputStream = new FileInputStream(path);
        }
        return inputStream;
    }


    /**
     * 获取文件上传的路径
     * @param file
     * @return
     */
    public static String getFilePath(String file){
        String suffix = getSuffix(file);
        String dateFormat = DateUtil.getDateFormat(new Date(), DateUtil.FILE_FORMAT);
        String path = "/"+ dateFormat + "/" + System.currentTimeMillis() + "." + suffix;
        return path;
    }

    /**
     * 获取后缀
     * @param url
     * @return
     */
    public static String getSuffix(String url){
        return url.substring(url.lastIndexOf(".")+1);
    }

    /**
     * 从网络流中获取输入流
     * @param fileUrl
     * @return
     */
    public static InputStream getInputStreamFromWeb(String fileUrl){
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(fileUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置网络连接超时时间
            httpURLConnection.setConnectTimeout(3000);
            // 设置应用程序要从网络连接读取数据
            httpURLConnection.setDoInput(true);


            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                // 从服务器返回一个输入流
                inputStream = httpURLConnection.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

}
