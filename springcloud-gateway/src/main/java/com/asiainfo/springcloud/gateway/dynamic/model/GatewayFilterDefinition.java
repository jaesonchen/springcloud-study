package com.asiainfo.springcloud.gateway.dynamic.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * filter 过滤器定义模型
 * 
 * @author chenzq  
 * @date 2019年5月26日 下午5:29:56
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved.
 */
public class GatewayFilterDefinition {

    // Filter Name
    private String name;
    // 对应的路由规则
    private Map<String, String> args = new LinkedHashMap<>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Map<String, String> getArgs() {
        return args;
    }
    public void setArgs(Map<String, String> args) {
        this.args = args;
    }
    @Override
    public String toString() {
        return "GatewayFilterDefinition [name=" + name + ", args=" + args + "]";
    }
}
