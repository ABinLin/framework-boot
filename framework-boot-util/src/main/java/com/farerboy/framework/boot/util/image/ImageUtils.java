package com.farerboy.framework.boot.util.image;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtils {

    public static File getUrlImage(String url,String folderPath){
        return getUrlImage(url,folderPath,null);
    }

    public static File getUrlImage(String url,String folderPath,String fileName){
        try {
            String[] split = url.split("\\.");
            String suffix = split[split.length-1];
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                byte[] bytes = readInputStream(inputStream);
                File file = new File(folderPath);
                if(!file.exists()){
                    file.mkdirs();
                }
                if(StringUtils.isNotBlank(fileName)){
                    file = new File(folderPath+fileName+"."+suffix);
                }else {
                    file = new File(folderPath+System.currentTimeMillis()+"."+suffix);
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(bytes);
                fileOutputStream.close();
                inputStream.close();
                return file;
            }
        } catch (IOException e) {
            System.out.println("获取网络图片出现异常，图片路径为：" + url);
            e.printStackTrace();
        }
        return null;
    }


    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static File change2jpg(File f) {
        try {
            Image        i  = Toolkit.getDefaultToolkit().createImage(f.getAbsolutePath());
            PixelGrabber pg = new PixelGrabber(i, 0, 0, -1, -1, true);
            pg.grabPixels();
            int              width      = pg.getWidth(), height = pg.getHeight();
            final int[]      RGB_MASKS  = { 0xFF0000, 0xFF00, 0xFF };
            final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
            DataBuffer       buffer     = new DataBufferInt((int[]) pg.getPixels(), pg.getWidth() * pg.getHeight());
            WritableRaster   raster     = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
            BufferedImage    img        = new BufferedImage(RGB_OPAQUE, raster, false, null);
            String path=f.getAbsolutePath().split("\\.")[0]+".jpg";
            File file =new File(path);
            ImageIO.write(img,"jpg",file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
//            throw new RPException("图片转换异常",e);
            return null;
        }
    }

    public static void zoomImage(String src,String dest,int w,int h,boolean proportion){
        if(!validFilePathExist(src)){
            throw new RuntimeException("源文件不存在："+src);
        }
        if(dest == null){
            dest = src;
        }
        File srcFile = new File(src);
        File destFile = new File(dest);
        double wr=0,hr=0;
        BufferedImage bufImg;
        try {
            bufImg = ImageIO.read(srcFile);//读取图片
        }catch (Exception e){
            throw new RuntimeException("图片读取异常："+src,e);
        }
//        Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH); //设置缩放目标图片模板
        wr=w*1.0/bufImg.getWidth();     //获取缩放比例
        hr=h*1.0 / bufImg.getHeight();
        if(proportion){
            double r = wr<hr?wr:hr;
            wr = r;
            hr = r;
        }
        try {
            ImageIO.write(resize(bufImg,wr,hr),dest.substring(dest.lastIndexOf(".")+1), destFile); //写入缩减后的图片
        } catch (Exception ex) {
            throw new RuntimeException("图片写入异常：",ex);
        }
    }

    public static void zoomImage(String src,String dest,int size) {
        if(!validFilePathExist(src)){
            throw new RuntimeException("源文件不存在："+src);
        }
        if(dest == null){
            dest = src;
        }
        File srcFile = new File(src);
        File destFile = new File(dest);
        long fileSize = srcFile.length();
        if(fileSize < size * 1024)   //文件大于size k时，才进行缩放,注意：size以K为单位
        {
            return;
        }

        Double rate = (size * 1024.00 * 5) / fileSize; // 获取长宽缩放比例
        BufferedImage bufImg;
        try {
            bufImg = ImageIO.read(srcFile);
        }catch (Exception e){
            throw new RuntimeException("图片读取异常："+src,e);
        }
        try {
            ImageIO.write(resize(bufImg,rate,rate),dest.substring(dest.lastIndexOf(".")+1), destFile);
        } catch (Exception ex) {
            throw new RuntimeException("图片写入异常：",ex);
        }
    }

    private static boolean validFilePathExist(String path){
        if(path ==null || !new File(path).exists()){
            return false;
        }
        return true;
    }

    private static BufferedImage resize(BufferedImage bufferedImage,double x,double y){
        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(x, y), null);
        return ato.filter(bufferedImage, null);
    }

//    public static void main(String[] args) {
////        String url = "https://uniview-center-dev-1258963190.cos.ap-guangzhou.myqcloud.com/faceDatabase/2019/10/14/9101df4ff830405c8b83ed99e51bacba.jpg";
////        getUrlImage(url,"D://aa.jpg");
////        File f =new File("D:\\1.png");
////        change2jpg(f);
//    }

    /*public static void main(String[] args) {
//        zoomImage("D:\\yiyu\\uniview\\upload\\unzip\\ce4f3400a28d4348b94ba7dfc33b3d76\\庄浪县思源实验学校_一年级_1班_陈铎.jpg","" +
//                "D:\\yiyu\\uniview\\upload\\unzip\\ce4f3400a28d4348b94ba7dfc33b3d76\\庄浪县思源实验学校_一年级_1班_陈铎_new.jpg",150);

//        zoomImage("D:\\yiyu\\uniview\\upload\\unzip\\ce4f3400a28d4348b94ba7dfc33b3d76\\庄浪县思源实验学校_一年级_1班_陈铎.jpg","" +
//                "D:\\yiyu\\uniview\\upload\\unzip\\ce4f3400a28d4348b94ba7dfc33b3d76\\庄浪县思源实验学校_一年级_1班_陈铎_new.jpg",640,640,true);
        *//*File file = new File("E:\\01_工作资料\\00_人脸\\甘肃\\庄浪县思源实验学校\\10后补学生\\12_压缩后");
        File[] files = file.listFiles();
        for (File f: files){
            zoomImage(f.getAbsolutePath(),null,1000,1000,true);
            String suffix = f.getName().split("\\.")[1];
            if(!suffix.equals("jpg")){
                if(suffix.equals("JPG")){
                    String fileName = f.getAbsolutePath().split("\\.")[0];
                    f.renameTo(new File(fileName+".jpg"));
                }
            }
        }*//*

        // 教师照片处理start
        String dealOutPath="E:\\01_工作资料\\00_人脸\\甘肃\\庄浪县思源实验学校\\00_教职工\\00_思源学校教职工照片\\33_处理后照片";
        String dealErrorOutPath="E:\\01_工作资料\\00_人脸\\甘肃\\庄浪县思源实验学校\\00_教职工\\00_思源学校教职工照片\\34_处理异常照片";
        File file = new File("E:\\01_工作资料\\00_人脸\\甘肃\\庄浪县思源实验学校\\00_教职工\\00_思源学校教职工照片\\32_压缩后");
        File[] files = file.listFiles();
        for (File f: files){
//            if(!f.getName().split("\\.")[1].equals("jpg")){
//                change2jpg(f);
//            }
            String name = f.getName().split("\\.")[0];
            String[] nameArr = name.split("_");
            if(nameArr.length!=3){
                System.out.println("处理异常："+f.getAbsolutePath());
                String errorName = dealErrorOutPath+"/"+f.getName();
                f.renameTo(new File(errorName));
                continue;
            }
            String code = GenerateCode.generateCode(6);
            String member = DateUtil.format(new Date(), "YYYYMMddHHmmss")+code+"_"+nameArr[1]+".jpg";
            f.renameTo(new File(dealOutPath+"/"+member));
        }
        // 教师照片处理end
        System.out.println("处理完成");
    }*/

}
