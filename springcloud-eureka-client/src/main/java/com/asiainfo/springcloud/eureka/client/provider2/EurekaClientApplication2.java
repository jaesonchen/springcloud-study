package com.asiainfo.springcloud.eureka.client.provider2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asiainfo.springcloud.eureka.client.provider1.EurekaClientApplication1;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月19日 下午3:03:16
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication2 {

    @Value("${server.port}")
    String port;

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable(value = "name") String name) {
        return "hello " + name + " , i am from port:" + port;
    }
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(new Class<?>[] { EurekaClientApplication1.class });
        app.setAdditionalProfiles(new String[] { "provider2" });
        app.run(args);
    }
}
