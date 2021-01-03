package com.crisper.server.oldImpl.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties("smart-home")
public class HomeProperties {
    private Map<String,Object> deviceMap =new HashMap();

    public Map<String, Object> getDeviceMap() {
        return deviceMap;
    }

    public void setDeviceMap(Map<String, Object> deviceMap) {
        this.deviceMap = deviceMap;
    }
}
