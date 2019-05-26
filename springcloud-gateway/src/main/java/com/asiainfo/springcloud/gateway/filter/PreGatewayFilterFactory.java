package com.asiainfo.springcloud.gateway.filter;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.style.ToStringCreator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.validation.annotation.Validated;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月26日 下午3:36:47
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
public class PreGatewayFilterFactory extends AbstractGatewayFilterFactory<PreGatewayFilterFactory.NameValueConfig> {

    final Logger logger = LoggerFactory.getLogger(getClass());
    
    final String REQUEST_TIME_BEGIN = "requestTimeBegin";
    final String KEY = "withParams";
    
    public PreGatewayFilterFactory() {
        super(NameValueConfig.class);
    }
    
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }
    
    @Override
    public GatewayFilter apply(NameValueConfig config) {

        return (exchange, chain) -> {
            //If you want to build a "pre" filter you need to manipulate the request before calling chain.filter
            // 创建新的 ServerHttpRequest
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header(config.getName(), config.getValue())
                    .build();
            //use builder to manipulate the request
            return chain.filter(exchange.mutate().request(request).build());
        };
    }
    
    public DataBuffer responseErrorInfo(ServerHttpResponse response , String status ,String message) throws UnsupportedEncodingException {
        
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        Map<String,String> map = new HashMap<>();
        map.put("status", status);
        map.put("message", message);
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(map.toString().getBytes("UTF-8"));
        return bodyDataBuffer;
    }
    
    @Validated
    public static class NameValueConfig {
        @NotEmpty
        protected String name;
        @NotEmpty
        protected String value;

        public String getName() {
            return name;
        }

        public NameValueConfig setName(String name) {
            this.name = name;
            return this;
        }

        public String getValue() {
            return value;
        }

        public NameValueConfig setValue(String value) {
            this.value = value;
            return this;
        }

        @Override
        public String toString() {
            return new ToStringCreator(this)
                    .append("name", name)
                    .append("value", value)
                    .toString();
        }
    }
}
