package com.farerboy.framework.boot.util.encryption;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Description 签名工具类
 * @Author linjb
 * @Date 2019/7/24 0024 21:49
 * @Version 1.0
 */
public class SignUtil {

    private static SignUtil signUtil;

    private SignUtil(){}

    // 单例
    public static SignUtil getInstance() {
        if (signUtil == null) {
            synchronized (MD5Util.class) {
                if (signUtil == null) {
                    signUtil = new SignUtil();
                }
            }
        }
        return signUtil;
    }

    /***********************与业务系统交互的签名工具**********************************/

    public boolean validParamSign(Object o,String secretKey,String sign){
        try {
            return generateParamSign(o,secretKey).equals(sign);
        }catch (Exception e){
            return false;
        }
    }

    public boolean validParamSignByMap(Map<String,String> map, String secretKey, String sign){
        try {
            return generateParamSignByMap(map,secretKey).equals(sign);
        }catch (Exception e){
            return false;
        }
    }

    /**
     * @Description 生成与业务系统交互的参数签名
     * @Date 2019/7/26 0026 17:05
     * @Author linjb
     * @param o
     * @param secretKey
     * @return java.lang.String
     */
    public String generateParamSign(Object o,String secretKey){
        StringBuffer sb=new StringBuffer();
        sb.append(getSignTempString(o))
                .append("secretKey=").append(secretKey);
        String sign=MD5Util.getInstance().encode(sb.toString());
        return sign;
    }

    /**
     * @Description 生成与业务系统交互的参数签名
     * @Date 2019/7/26 0026 19:44
     * @Author linjb
     * @param map
     * @param secretKey
     * @return java.lang.String
     */
    public String generateParamSignByMap(Map<String,String> map,String secretKey){
        StringBuffer sb=new StringBuffer();
        sb.append(getSignTempStringByMap(map))
                .append("secretKey=").append(secretKey);
        String sign=MD5Util.getInstance().encode(sb.toString());
        return sign;
    }



    /***********************验证数据库记录签名工具**********************************/
    /**
     * @Description 验证数据库记录真实性
     * @Date 2019/7/25 0025 19:24
     * @Author linjb
     * @param o
     * @param sign
     * @param encodeRules
     * @return boolean
     */
    public boolean validateRecordSign(Object o,String sign,String encodeRules) {
        if(sign==null){
            return false;
        }
        try {
            return sign.equals(generateRecordSign(o,encodeRules));
        }catch (Exception e){
            throw new RuntimeException("方法com.yiyu.core.utils.SignUtil.validateRecordSign中数据加密异常:",e);
        }
    }

    /**
     * @Description 生成数据库记录签名
     * @Date 2019/7/25 0025 19:25
     * @Author linjb
     * @param
     * @return java.lang.String
     */
    public String generateRecordSign(Object o,String encodeRules) {
        return MD5Util.getInstance().encode(getSignTempString(o),encodeRules);
    }


    /***********************通用方法，生成待签名的字符串**********************************/

    /**
     * @Description 根据对象获取无密钥的待签名字符串
     * @Date 2019/7/26 0026 16:57
     * @Author linjb
     * @param o
     * @return java.lang.String
     */
    public String getSignTempString(Object o) {
        try {
            ArrayList<String> list = new ArrayList<>();
            Class cls = o.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                if(f.getName().equals("sign")){
                    continue;
                }
                if(f.get(o) instanceof Date){
                    continue;
                }
                if (f.get(o) != null && !"".equals(f.get(o).toString().trim()) ) {
                    list.add(f.getName() + "=" + f.get(o).toString().trim() + "&");
                }
            }
            int size = list.size();
            if(size==0){
                return null;
            }
            String [] arrayToSort = list.toArray(new String[size]);
            Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < size; i ++) {
                sb.append(arrayToSort[i]);
            }
            String result = sb.toString();
            return result;
        }catch (Exception e){
            throw new RuntimeException("根据对象获取无密钥的待签名字符串异常",e);
        }

    }

    /**
     * @Description 根据map获取无密钥的待签名字符串
     * @Date 2019/7/26 0026 16:59
     * @Author linjb
     * @param map
     * @return java.lang.String
     */
    public String getSignTempStringByMap(Map<String, String> map) {
        StringBuilder sb = new StringBuilder((map.size() + 1) * 10);
        List<String> keys = new ArrayList<String>(map.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            if(key.equals("sign")){
                continue;
            }
            String value=map.get(key);
            if (null !=value && value.trim().length() > 0) {
                sb.append(key).append("=");
                sb.append(map.get(key));
                sb.append("&");
            }
        }
        return sb.toString();
    }

    public String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Throwable e) {
            return str;
        }
    }

    /**
     * @Description 对需要加密的内容使用base64加盐加密，再MD5加密
     * @Date 2019/11/9 0009 13:11
     * @Author linjb
     * @param content
     * @param salt
     * @return java.lang.String
     */
    public String encodeMD5ForAESAddSalt(String content,String salt){
        try {
            return MD5Util.getInstance().encode(AESUtil.encrypt(content,salt));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
