package com.asiainfo.springcloud.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月19日 下午4:21:08
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@Component
public class HelloServiceFallback implements HelloService {

    final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public String sayHelloFromClient(String name) {
        logger.info("HelloServiceFallback invoked. param = {}", name);
        return "sorry " + name;
    }
}
