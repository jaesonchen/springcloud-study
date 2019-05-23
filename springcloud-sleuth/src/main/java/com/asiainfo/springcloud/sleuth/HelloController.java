package com.asiainfo.springcloud.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月19日 下午3:31:07
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@RestController
public class HelloController {

    final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    HelloService service;
    
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable(value = "name") String name) {
        logger.info("hello invoked. param = {}", name);
        return service.hiService(name);
    }
    
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() {
        logger.info("DiscoveryClient invoked. services: {}", discoveryClient.getServices());
        return "Services: " + discoveryClient.getServices();
    }
}
