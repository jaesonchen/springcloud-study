package com.asiainfo.springcloud.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月26日 下午2:53:53
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
public class RequestTimeFilter implements GatewayFilter, Ordered {

    final Logger logger = LoggerFactory.getLogger(getClass());
    final String REQUEST_TIME_BEGIN = "requestTimeBegin";
    
    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                    if (startTime != null) {
                        logger.info("{} : {}ms", exchange.getRequest().getURI().getRawPath(), System.currentTimeMillis() - startTime);
                    }
                })
        );
    }
}
