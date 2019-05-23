package com.asiainfo.springcloud.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月19日 下午4:45:16
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@Service
public class HelloService {

    final Logger logger = LoggerFactory.getLogger(getClass());
    
    public static final String SERVICE_NAME = "service-hello";
    
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String hello(String name) {
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/hello/{name}", String.class, name);
    }
    
    // fallback
    public String helloFallback(String name, Throwable ex) {
        logger.error("timeout on invoke service-hello!", ex);
        return "hi, " + name + ", sorry error!";
    }
}
