package com.asiainfo.springcloud.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月23日 下午9:21:44
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientApplication {
    
    // curl -X POST http://localhost:9080/actuator/refresh
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}
