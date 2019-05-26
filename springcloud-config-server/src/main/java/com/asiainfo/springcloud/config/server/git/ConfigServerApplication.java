package com.asiainfo.springcloud.config.server.git;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月23日 下午9:22:26
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
    
    // http://localhost:8001/mcd-config/dev
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
