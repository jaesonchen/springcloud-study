package com.asiainfo.springcloud.gateway.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月26日 下午2:57:47
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@SpringBootApplication
public class RequestTimeFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RequestTimeFilterApplication.class, args);
    }
    
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
       return builder.routes()
               .route(p -> p
                       .path("/customer/**")
                       .filters(f -> f.filter(new RequestTimeFilter()).addRequestHeader("X-Response-Default-Foo", "Bar"))
                       .uri("http://localhost:8080"))
               .build();
    }
}
