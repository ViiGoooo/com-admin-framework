package com.admin.framework.component.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.*;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author zsw
 * @Description
 * @Date Create in 14:59 2019\8\8 0008
 */
public class ImageUtil {


    /**
     * 生成圆角图片
     * @param path
     * @return
     */
    public static BufferedImage transferImgForRoundImage(String path){
        try{
            BufferedImage newImage = squareToRectangle(path);
            return transferImgForRoundImage(newImage);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将长图截取为正方形(获取中间最大的正方形)
     * @param path
     * @return
     * @throws IOException
     */
    public static BufferedImage squareToRectangle(String path) throws IOException {
        InputStream inputStream = FileUtil.getInputStreamByPath(path);
        String suffix = FileUtil.getSuffix(path);
        return squareToRectangle(inputStream,suffix);
    }

    /**
     * 获取图片尺寸
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static ImageData getSquareData(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArray = IOUtil.cloneInputStream(inputStream);
        InputStream source = new ByteArrayInputStream(byteArray.toByteArray());
        BufferedImage image = ImageIO.read(source);
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        ImageData imageData = new ImageData();
        imageData.setImage(image);
        if(sourceWidth == sourceHeight){
            imageData.setHeight(sourceHeight);
            imageData.setWidth(sourceWidth);
            imageData.setX(0);
            imageData.setY(0);
            return imageData;
        }
        int x = 0,y = 0,width = 0,height = 0;
        if(sourceWidth > sourceHeight){
            x = (sourceWidth - sourceHeight) / 2;
            x = Math.round(x);
            width = sourceHeight;
            height = sourceHeight;
        }
        if(sourceHeight > sourceWidth){
            y = (sourceHeight - sourceWidth) / 2;
            x = Math.round(x);
            width = sourceHeight;
            height = sourceHeight;
        }
        imageData.setHeight(height);
        imageData.setWidth(width);
        imageData.setX(x);
        imageData.setY(y);
        return imageData;
    }

    /**
     * 将长图截取为正方形(获取中间最大的正方形)
     * @param inputStream
     * @param suffix
     * @return
     * @throws IOException
     */
    public static BufferedImage squareToRectangle(InputStream inputStream, String suffix) throws IOException {
        ByteArrayOutputStream byteArray = IOUtil.cloneInputStream(inputStream);
        InputStream source = new ByteArrayInputStream(byteArray.toByteArray());
        InputStream operation = new ByteArrayInputStream(byteArray.toByteArray());
        ImageData squareData = getSquareData(source);
        ImageInputStream iis = ImageIO.createImageInputStream(operation);
        Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(suffix); //ImageReader声称能够解码指定格式
        ImageReader reader = it.next();
        reader.setInput(iis, true); //将iis标记为true（只向前搜索）意味着包含在输入源中的图像将只按顺序读取
        ImageReadParam param = reader.getDefaultReadParam(); //指定如何在输入时从 Java Image I/O框架的上下文中的流转换一幅图像或一组图像
        Rectangle rect = new Rectangle(squareData.getX(), squareData.getY(), squareData.getWidth() , squareData.getHeight()); //定义空间中的一个区域
        param.setSourceRegion(rect); //提供一个 BufferedImage，将其用作解码像素数据的目标。
        BufferedImage result = reader.read(0, param); //读取索引imageIndex指定的对象
        return result;
    }


    /**
     * 图片原形（如果圆图是长方形则会返回椭圆）
     * @param buffImg1
     * @return
     */
    public static BufferedImage transferImgForRoundImage(BufferedImage buffImg1){
        BufferedImage resultImg = null;
        resultImg = new BufferedImage(buffImg1.getWidth(), buffImg1.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resultImg.createGraphics();
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, buffImg1.getWidth(), buffImg1.getHeight());
        // 使用 setRenderingHint 设置抗锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        resultImg = g.getDeviceConfiguration().createCompatibleImage(buffImg1.getWidth(), buffImg1.getHeight(),
                Transparency.TRANSLUCENT);
        //g.fill(new Rectangle(buffImg2.getWidth(), buffImg2.getHeight()));
        g = resultImg.createGraphics();
        // 使用 setRenderingHint 设置抗锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setClip(shape);
        g.drawImage(buffImg1, 0, 0, null);
        g.dispose();
        return resultImg;
    }


    /**
     *
     * @param originalFile  原文件
     * @param resizedFile  压缩目标文件
     * @param newWidth  压缩后的图片宽度
     * @param quality  压缩质量（0到1之间，越高质量越好）
     * @throws IOException
     */
    public static void resize(File originalFile, File resizedFile,
                              int newWidth, float quality) throws IOException {

        if (quality > 1) {
            throw new IllegalArgumentException("Quality has to be between 0 and 1");
        }

        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
        Image i = ii.getImage();
        Image resizedImage = null;

        int iWidth = i.getWidth(null);
        int iHeight = i.getHeight(null);
        if (iWidth > iHeight) {
            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight) / iWidth, Image.SCALE_SMOOTH);
        } else {
            resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight, newWidth, Image.SCALE_SMOOTH);
        }
        // 获取图片中的所有像素
        Image temp = new ImageIcon(resizedImage).getImage();
        // 创建缓冲
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_RGB);
        // 复制图片到缓冲流中
        Graphics g = bufferedImage.createGraphics();
        // 清除背景并开始画图
        g.setColor(Color.white);
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
        g.drawImage(temp, 0, 0, null);
        g.dispose();
        // 柔和图片.
        float softenFactor = 0.05f;
        float[] softenArray = { 0, softenFactor, 0, softenFactor,
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null);

        FileOutputStream out = new FileOutputStream(resizedFile);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
        param.setQuality(quality, true);
        encoder.setJPEGEncodeParam(param);
        encoder.encode(bufferedImage);
        bufferedImage.flush();
        out.close();
    }

    /**
     * 获取图片宽度和高度
     *
     * @param file 图片路径
     * @return 返回图片的宽度
     */
    public static int[] getImgWidthHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = { 0, 0 };
        try {
            // 获得文件输入流
            is = new FileInputStream(file);
            // 从流里将图片写入缓冲图片区
            src = ImageIO.read(is);
            // 得到源图片宽
            result[0] =src.getWidth(null);
            // 得到源图片高
            result[1] =src.getHeight(null);
            is.close();  //关闭输入流
        } catch (Exception ef) {
            ef.printStackTrace();
        }
        return result;
    }

    /**
     * 获取画布
     * @param background
     * @return
     */
    public static Graphics2D getGraphics2D(BufferedImage background){
        Graphics2D g = background.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        return g;
    }

    /**
     * 转inputstream
     * @param bufferedImage
     * @return
     * @throws IOException
     */
    public static InputStream BufferedToInputstream(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
        ImageIO.write(bufferedImage, "png", imOut);
        InputStream inputStream = new ByteArrayInputStream(bs.toByteArray());
        bs.close();
        imOut.close();
        return inputStream;
    }


    public static void main(String[] args) throws IOException {
        String back = "C:\\Users\\Administrator\\Desktop\\成氏之家\\crowdfunding_template.png";
        BufferedImage background = ImageIO.read(new File(back));
        Graphics2D g = getGraphics2D(background);
    }

    @Data
    public static class ImageData{
        private int x;
        private int y;
        private int width;
        private int height;
        private BufferedImage image;
    }


}
