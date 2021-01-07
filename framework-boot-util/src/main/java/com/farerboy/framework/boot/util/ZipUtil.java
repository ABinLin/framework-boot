package com.farerboy.framework.boot.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 文件压缩工具类
 */
public class ZipUtil {
    /**
     * zip解压
     */
    public static void unZip(File srcFile, String destDirPath) {
        try {
            ZipFile zipFile = new ZipFile(srcFile);
            zipFile.setFileNameCharset("GBK");
            zipFile.extractAll(destDirPath);
        } catch (ZipException e) {
            throw new RuntimeException("解压文件失败",e);
        }
    }
    /**
     * 功能描述:压缩文件 sourcePath 要打包的文件夹 targetPath压缩到哪个文件
     */
    public static void zipFile(String sourcePath,String targetPath,boolean isDeleteSourcePath) throws Exception {
        // 生成的压缩文件
        ZipFile zipFile = new ZipFile(targetPath);
        ZipParameters parameters = new ZipParameters();
        // 压缩方式
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        // 压缩级别
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        // 要打包的文件夹
        File currentFile = new File(sourcePath);
        File[] fs = currentFile.listFiles();
        //文件夹下所有的文件、文件夹
        for (File f : fs) {
            if (f.isDirectory()) {
                zipFile.addFolder(f.getPath(), parameters);
            } else {
                zipFile.addFile(f, parameters);
            }
        }
        if(isDeleteSourcePath){
            FileUtils.deleteDirectory(currentFile);
        }
    }

    /**
     * 验证ZIP文件中是否都是某种文件
     */
    public static boolean validZipFile(String sourcePath, List<String> suffixList){
        boolean result =false;
        if(suffixList == null || suffixList.size()==0){
            return result;
        }
        FileInputStream fileInputStream=null;
        InputStream in = null;
        ZipInputStream zin = null;
        try {
            fileInputStream = new FileInputStream(sourcePath);
            in = new BufferedInputStream(fileInputStream);
            Charset gbk = Charset.forName("gbk");
            zin = new ZipInputStream(in,gbk);
            ZipEntry ze;
            while((ze = zin.getNextEntry()) != null){
                result = false;
                for(String s:suffixList){
                    if(ze.toString().endsWith(s)){
                        result=true;
                        break;
                    }
                }
                if(!result){
                    return result;
                }
            }
            return result;
        }catch (Exception e){
            throw new RuntimeException("验证ZIP文件异常",e);
        }finally {
            try {
                zin.closeEntry();
                zin.close();
                zin = null;
                in.close();
                in = null;
                fileInputStream.close();
                fileInputStream = null;
            }catch (Exception e){

            }
        }
    }
    
    /*public static void main(String[] args) {
        *//*List<String> list =new ArrayList<>();
        list.add("png");
        list.add("jpg");
    	System.out.println(validZipFile("D:\\1.zip",list));*//*
    }*/
}

