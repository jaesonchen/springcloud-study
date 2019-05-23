package com.asiainfo.springcloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class ConfigClientApplication {

    @Value("${mcd.welcome}")
    private String welcome;

    @RequestMapping("/hello")
    public String hello() {
        return welcome;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}
