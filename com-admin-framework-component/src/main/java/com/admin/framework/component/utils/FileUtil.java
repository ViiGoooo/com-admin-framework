package com.admin.framework.component.utils;



import java.io.*;
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
     * 获取文件内容
     * @param filePath
     * @return
     */
    public static String getFileContent(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            throw new RuntimeException("文件不存在");
        }
        try {
            FileReader reader = new FileReader(file);
            BufferedReader breader = new BufferedReader(reader);
            String line = "";
            String result = "";
            while ((line = breader.readLine()) != null){
                result += line + "\n";
            }
            return result.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件路径
     * @param fileName 文件名称
     * @param suffix   文件后缀
     * @param base      文件根路径
     * @return
     */
    public static Map getFilePath(String fileName,String suffix,String base){
        String date = DateUtil.getCurrentDateFormat(DateUtil.FILE_FORMAT);

        String path = fileName + "/" + date + "/" + System.currentTimeMillis() + "." + suffix;

        String filePath =  base + "/" + path;
        File dir = new File(base + "/" + fileName + "/" + date + "/");
        if(!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(filePath);

        Map result = new HashMap(16);
        result.put("file",file);
        result.put("path",path);
        return result;
    }

    /**
     * 获取文件
     * @param filePath
     * @return
     */
    public static File getFile(String filePath){
        String dir = filePath.substring(0,filePath.lastIndexOf("/"));
        String name = filePath.substring(filePath.lastIndexOf("/"),filePath.length());
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
            Long file_length = file.length();
            byte[] file_content = new byte[file_length.intValue()];
            FileInputStream in = new FileInputStream(file);
            in.read(file_content);
            in.close();
            return new String(file_content,encoding);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
