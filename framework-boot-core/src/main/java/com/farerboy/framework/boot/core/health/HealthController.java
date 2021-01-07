package com.farerboy.framework.boot.core.health;

import com.alibaba.fastjson.JSONObject;
import com.farerboy.framework.boot.core.annotation.OpenRouting;
import com.farerboy.framework.boot.common.dto.ServerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监控检测接口
 * @author farerboy
 */
@RestController
public class HealthController {

//    @Value("${yiyu.app.version:0.0.1}")
//    private String appVersion;
//
//    @Value("${yiyu.app.build.time:null}")
//    private String appBuildTime;

    @OpenRouting
    @GetMapping("health")
    public ServerResponse health(){
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("version",appVersion);
//        jsonObject.put("buildTime",appBuildTime);
        return ServerResponse.createBySuccess("OK",jsonObject);
    }
}
