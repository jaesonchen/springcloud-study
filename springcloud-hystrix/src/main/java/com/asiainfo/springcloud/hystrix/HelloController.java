package com.asiainfo.springcloud.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    HelloService service;
    
    @RequestMapping("/hi/{name}")
    public String hi(@PathVariable(value = "name") String name) {
        System.out.println("Hystric HelloController.hi is invoked!");
        return service.hiService(name);
    }
}