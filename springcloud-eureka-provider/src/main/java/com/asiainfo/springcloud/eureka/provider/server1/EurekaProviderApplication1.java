package com.asiainfo.springcloud.eureka.provider.server1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月19日 下午3:03:16
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.asiainfo.springcloud.eureka.provider.service")
public class EurekaProviderApplication1 {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(new Class<?>[] { EurekaProviderApplication1.class });
        app.setAdditionalProfiles(new String[] { "provider1" });
        app.run(args);
    }
}
