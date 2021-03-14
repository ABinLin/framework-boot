package com.farerboy.framework.boot.server.sdk.huawei.sms;

/**
 * TODO description
 * 2021/3/14 5:12 下午
 *
 * @author linjianbin
 */
public interface SmsService {

    /**
     * 发送验证码
     */
    void sendVerificationCode(String phone);

    /**
     * 发送指定验证码
     */
    void sendVerificationCode(String phone,String code);

    /**
     * 校验验证码
     */
    boolean checkVerificationCode(String phone, String code);
}
