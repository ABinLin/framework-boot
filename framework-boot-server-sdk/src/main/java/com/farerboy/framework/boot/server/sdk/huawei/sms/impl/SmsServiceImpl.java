package com.farerboy.framework.boot.server.sdk.huawei.sms.impl;

import com.farerboy.framework.boot.common.enums.ResponseCode;
import com.farerboy.framework.boot.common.exception.BaseException;
import com.farerboy.framework.boot.common.exception.ValidException;
import com.farerboy.framework.boot.server.sdk.huawei.sms.SmsService;
import com.farerboy.framework.boot.server.sdk.huawei.sms.properties.SmsProperties;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * TODO description
 * 2021/3/14 5:12 下午
 *
 * @author linjianbin
 */
@Component("smsService")
public class SmsServiceImpl implements SmsService {
    private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
    /**
     * 无需修改,用于格式化鉴权头域,给"X-WSSE"参数赋值
     */
    private static final String WSSE_HEADER_FORMAT = "UsernameToken Username=\"%s\",PasswordDigest=\"%s\",Nonce=\"%s\",Created=\"%s\"";
    /**
     * 无需修改,用于格式化鉴权头域,给"Authorization"参数赋值
     */
    private static final String AUTH_HEADER_VALUE = "WSSE realm=\"SDP\",profile=\"UsernameToken\",type=\"Appkey\"";

    private static final long TIME_OUT = 300;

//    @Autowired
//    private RedisUtil redisUtil;

    @Autowired
    private SmsProperties smsProperties;

    @Override
    public void sendVerificationCode(String phone) {
        String code = String.valueOf((int)(Math.random()*900000 + 100000));
        sendVerificationCode(phone, code);
    }

    @Override
    public void sendVerificationCode(String phone, String code) {
        if(null == code || "".equals(code.trim())){
            code = String.valueOf((int)(Math.random()*900000 + 100000));
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        boolean flag =  pattern.matcher(code).matches();
        if(!flag){
            throw new ValidException("Verification code is not a number.");
        }
        //请求Body,不携带签名名称时,signature请填null
        String templateParas = "[\""+ code +"\"]";
        String body = buildRequestBody(smsProperties.getSender(), phone, smsProperties.getTemplateId(), templateParas, "", smsProperties.getSignature());
        //请求Headers中的X-WSSE参数值
        String wsseHeader = buildWsseHeader(smsProperties.getAppKey(), smsProperties.getAppSecret());
        //如果JDK版本是1.8,可使用如下代码
        //为防止因HTTPS证书认证失败造成API调用失败,需要先忽略证书信任问题
        CloseableHttpClient client = null;
        try {
            client = HttpClients.custom()
                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null,
                            (x509CertChain, authType) -> true).build())
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();
            HttpResponse response = client.execute(RequestBuilder.create("POST")//请求方法POST
                    .setUri(smsProperties.getUrl())
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .addHeader(HttpHeaders.AUTHORIZATION, AUTH_HEADER_VALUE)
                    .addHeader("X-WSSE", wsseHeader)
                    .setEntity(new StringEntity(body)).build());

            //打印响应头域信息
            logger.info("Huawei sms response header = {}",response.toString());
            //打印响应消息实体
            logger.info("Huawei sms response body = {}",response.toString());
            String key = smsProperties.getSmsKey() + "_" + phone;
//            redisUtil.set(key, code,TIME_OUT);
        } catch (Exception e) {
            throw new BaseException(ResponseCode.HTTP_INTERFACE_ERROR.getCode(),"Huawei sms request error !",e);
        }
    }

    @Override
    public boolean checkVerificationCode(String phone, String code) {
//        CheckResult result = new CheckResult();
//        String key = SmsConfig.getSmsKey()+"_"+phone;
//        String verificationCode = (String)redisUtil.get(key);
//        if(null == verificationCode || "".equals(verificationCode)){
//            result.setStatus(false);
//            result.setMsg("验证码已过期");
//            return result;
//        }
//        if (!verificationCode.equals(code)){
//            result.setStatus(false);
//            result.setMsg("验证码不正确");
//            return result;
//        }
//        result.setStatus(true);
//        redisUtil.del(key);
//        return result;
        return false;
    }

    private String buildRequestBody(String sender, String receiver, String templateId, String templateParas,
                                    String statusCallbackUrl, String signature) {
        if (null == sender || null == receiver || null == templateId || sender.isEmpty() || receiver.isEmpty()
                || templateId.isEmpty()) {
            logger.error("Huawei sms necessary parameters receiver or templateId can not be null !");
            throw new ValidException("Huawei sms necessary parameters receiver or templateId can not be null !");
        }
        List<NameValuePair> keyValues = new ArrayList<NameValuePair>();

        keyValues.add(new BasicNameValuePair("from", sender));
        keyValues.add(new BasicNameValuePair("to", receiver));
        keyValues.add(new BasicNameValuePair("templateId", templateId));
        if (null != templateParas && !templateParas.isEmpty()) {
            keyValues.add(new BasicNameValuePair("templateParas", templateParas));
        }
        if (null != statusCallbackUrl && !statusCallbackUrl.isEmpty()) {
            keyValues.add(new BasicNameValuePair("statusCallback", statusCallbackUrl));
        }
        if (null != signature && !signature.isEmpty()) {
            keyValues.add(new BasicNameValuePair("signature", signature));
        }

        return URLEncodedUtils.format(keyValues, Charset.forName("UTF-8"));
    }
    private String buildWsseHeader(String appKey, String appSecret) {
        if (null == appKey || null == appSecret || appKey.isEmpty() || appSecret.isEmpty()) {
            logger.error("Huawei sms necessary parameters app-key or app-secret can not be null !");
            throw new ValidException("Huawei sms necessary parameters app-key or app-secret can not be null !");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String time = sdf.format(new Date());
        String nonce = UUID.randomUUID().toString().replace("-", "");
        byte[] passwordDigest = DigestUtils.sha256(nonce + time + appSecret);
        String hexDigest = Hex.encodeHexString(passwordDigest);

        //如果JDK版本是1.8,请加载原生Base64类,并使用如下代码
        //PasswordDigest
        String passwordDigestBase64Str = Base64.getEncoder().encodeToString(hexDigest.getBytes());
        //如果JDK版本低于1.8,请加载三方库提供Base64类,并使用如下代码
        //String passwordDigestBase64Str = Base64.encodeBase64String(hexDigest.getBytes(Charset.forName("utf-8"))); //PasswordDigest
        //若passwordDigestBase64Str中包含换行符,请执行如下代码进行修正
        //passwordDigestBase64Str = passwordDigestBase64Str.replaceAll("[\\s*\t\n\r]", "");

        return String.format(WSSE_HEADER_FORMAT, appKey, passwordDigestBase64Str, nonce, time);
    }
}
