package com.asiainfo.springcloud.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月19日 下午4:03:04
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@RestController
public class HelloController {

    final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    HelloService service;
    
    @RequestMapping("/hello/{name}")
    public String hi(@PathVariable(value = "name") String name) {
        logger.info("hello invoked. param = {}", name);
        return service.sayHelloFromClient(name);
    }
}
