package com.asiainfo.springcloud.consul.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月21日 下午8:50:01
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulProviderApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ConsulProviderApplication.class, args);
    }
}
