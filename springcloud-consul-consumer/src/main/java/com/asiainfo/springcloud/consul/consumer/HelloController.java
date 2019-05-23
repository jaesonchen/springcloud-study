package com.asiainfo.springcloud.consul.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月21日 下午9:10:19
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@RestController
public class HelloController {

    @Autowired
    private LoadBalancerClient loadBalancer;
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @RequestMapping("/services")
    public Object services() {
        return discoveryClient.getInstances("service-hello");
    }
    
    @RequestMapping("/discover")
    public Object discover() {
        return loadBalancer.choose("service-hello").getUri().toString();
    }
    
    @RequestMapping("/hello/{name}")
    public Object hello(@PathVariable(value = "name") String name) {
        ServiceInstance serviceInstance = loadBalancer.choose("service-hello");
        return new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/hello/{name}", String.class, name);
    }
}
