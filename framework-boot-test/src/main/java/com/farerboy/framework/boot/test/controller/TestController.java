package com.farerboy.framework.boot.test.controller;

import com.farerboy.framework.boot.common.dto.ServerResponse;
import com.farerboy.framework.boot.server.sdk.huawei.sms.SmsService;
import com.farerboy.framework.boot.test.params.TestConvertParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/3 2:29 下午
 */
@RestController
public class TestController {

    @PostMapping("testConvert")
    public ServerResponse testConvert(TestConvertParam testConvertParam){

        Map<String,Object> map = new HashMap<>();
        map.put("data",new Date());
        map.put("number" , Long.valueOf(123));
        map.put("string","string");
        map.put("LocalDate",LocalDate.now());
        return ServerResponse.createBySuccess(map);
    }

    @Autowired
    private SmsService smsService;
    @GetMapping("send")
    public ServerResponse send(String phone){
        smsService.sendVerificationCode(phone);
        return ServerResponse.createBySuccess();

    }
}
