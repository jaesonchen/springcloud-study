package com.asiainfo.springcloud.ribbon;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月22日 下午9:20:17
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@Configuration
public class RibbonConfig {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    // 默认负载均衡策略 RoundRobinRule
    @Bean
    public IRule loadBalancerRule() {
        return new MyLoadBalancerRule();
    }
}
