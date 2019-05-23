package com.asiainfo.springcloud.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月19日 下午3:28:41
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RibbonApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
    }
}
