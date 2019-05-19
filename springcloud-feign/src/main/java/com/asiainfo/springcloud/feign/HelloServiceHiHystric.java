package com.asiainfo.springcloud.feign;

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
public class HelloServiceHiHystric implements HelloService {

    @Override
    public String sayHiFromClient(String name) {
        return "sorry " + name;
    }
}
