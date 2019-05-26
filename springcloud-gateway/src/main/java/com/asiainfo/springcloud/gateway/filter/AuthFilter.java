package com.asiainfo.springcloud.gateway.filter;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月26日 下午1:08:18
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
public class AuthFilter implements GlobalFilter, Ordered {

    final Logger logger = LoggerFactory.getLogger(getClass());
            
    @Override
    public int getOrder() {
        return -10;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String token = request.getQueryParams().getFirst("token");
        if (StringUtils.isEmpty(token)) {
            logger.info( "token is empty ..." );
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            DataBuffer buffer = response.bufferFactory().wrap("{\"errorCode\": 401, \"errorMessage\": \"非法请求\"}".getBytes(StandardCharsets.UTF_8));
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            //response.setComplete();
            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }
}
