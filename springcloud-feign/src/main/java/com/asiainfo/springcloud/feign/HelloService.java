package com.asiainfo.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月19日 下午3:58:32
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@FeignClient(value = "service-hello", fallback = HelloServiceFallback.class)
public interface HelloService {
    
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    String sayHelloFromClient(@PathVariable(value = "name") String name);
}
