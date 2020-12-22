package com.farerboy.framework.boot.core.health;

import com.alibaba.fastjson.JSONObject;
import com.farerboy.framework.boot.core.annotation.OpenRouting;
import com.farerboy.framework.boot.core.annotation.UnencryptedResponse;
import com.framework.boot.common.dto.ServerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author linjb
 * @Date 2020/3/30 0030 11:19
 * @Version 1.0
 */
@RestController
public class HealthController {

    @Value("${yiyu.app.version:0.0.1}")
    private String appVersion;

    @Value("${yiyu.app.build.time:null}")
    private String appBuildTime;

    @UnencryptedResponse
    @OpenRouting
    @GetMapping("health")
    public ServerResponse health(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("version",appVersion);
        jsonObject.put("buildTime",appBuildTime);
        return ServerResponse.createBySuccess("OK",jsonObject);
    }
}
