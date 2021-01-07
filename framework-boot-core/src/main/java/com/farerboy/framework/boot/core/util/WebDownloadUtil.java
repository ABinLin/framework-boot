package com.farerboy.framework.boot.core.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 *
 * @author farerboy
 */
public class WebDownloadUtil {

    private Logger logger=LoggerFactory.getLogger(getClass());

    public static void downloadByInputStream(HttpServletRequest req, HttpServletResponse resp,
                                      InputStream inputStream,String fileName) throws IOException {
//        resp.addHeader("Content-Disposition","attachment;filename=" + fileName+";filename*=utf-8''"+fileName);
        resp.addHeader("Content-Disposition","attachment;filename*=UTF-8''"+URLEncoder.encode(fileName, "UTF-8"));
        resp.setContentType("application/octet-stream"); //设置文件MIME类型
        OutputStream out =null;
        try {
            out = resp.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedOutputStream bos = new BufferedOutputStream(out);
            byte[] buff = new byte[20480];
            int b = 0;
            while (-1 != (b = bis.read(buff))) {
                bos.write(buff, 0, b);
            }
            bis.close();
            bos.flush();
            bos.close();
        }finally {
            if(out!=null)
                out.close();
            if(inputStream!=null)
                inputStream.close();
        }
    }

    /**
     * http响应文件工具类
     */
    public static void writeZip(File file,HttpServletRequest request, HttpServletResponse response) throws IOException {
        OutputStream toClient = null;
        InputStream fis = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            String disposition = "attachment;filename=";
            if(request!=null&&request.getHeader("USER-AGENT")!=null&& StringUtils.contains(request.getHeader("USER-AGENT"), "Firefox")){
                disposition += new String(file.getName().getBytes(),"ISO8859-1");
            }else{
                disposition += URLEncoder.encode(file.getName(), "UTF-8");
            }
            // 设置response的Header
            response.addHeader("Content-Length", "" + file.length());
            response.addHeader("Content-disposition", disposition);
            response.setContentType("application/x-zip-compressed");
            toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        }catch (IOException e){
            throw e;
        }finally {
            if(toClient != null){
                toClient.close();
            }
            if(fis != null){
                fis.close();
            }
        }

    }
}
