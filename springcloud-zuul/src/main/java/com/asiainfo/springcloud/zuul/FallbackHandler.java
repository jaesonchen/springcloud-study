package com.asiainfo.springcloud.zuul;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**   
 * @Description: zuul 服务降级
 * 
 * @author chenzq  
 * @date 2019年7月7日 下午2:44:04
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@Component
public class FallbackHandler implements FallbackProvider {

    final Logger logger = LoggerFactory.getLogger(FallbackHandler.class);
    
    @Override
    public String getRoute() {
        // 支持服务降级的服务id，如果需要所有调用都支持回退，则返回 "*"
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause != null) {
            String reason = cause.getMessage();
            logger.info("Excption {}",reason);
        }
        return fallbackResponse(route);
    }
    
    public ClientHttpResponse fallbackResponse(String route) {
        
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                // 网关向api服务请求是失败了，但是消费者客户端向网关发起的请求是OK的，不应该把api的404, 500等问题抛给客户端
                return HttpStatus.OK;
            }
            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }
            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }
            @Override
            public void close() {

            }
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream((route + " is unavailable.").getBytes());
            }
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                // ContentType必须是request里accept的类型
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}