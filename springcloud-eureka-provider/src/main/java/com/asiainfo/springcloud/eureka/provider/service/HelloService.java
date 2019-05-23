package com.asiainfo.springcloud.eureka.provider.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月22日 下午9:07:51
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@RestController
public class HelloService {

    @Value("${server.port}")
    String port;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable(value = "name") String name) {
        return "hello " + name + " , i am from port:" + port;
    }
}
