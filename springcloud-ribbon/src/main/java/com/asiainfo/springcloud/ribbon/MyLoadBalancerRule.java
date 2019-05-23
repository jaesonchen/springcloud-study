package com.asiainfo.springcloud.ribbon;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月22日 下午9:24:24
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
public class MyLoadBalancerRule extends AbstractLoadBalancerRule {

    final Logger logger = LoggerFactory.getLogger(getClass());
    
    private AtomicInteger count = new AtomicInteger(0);
    
    private Server choose(ILoadBalancer lb, Object key) {
        
        if (lb == null) {
            return null;
        }
        
        Server server = null;
        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            //List<Server> allList = lb.getAllServers();
            int serverCount = upList.size();
            if (serverCount == 0) {
                return null;
            }

            int index = Math.abs(count.getAndIncrement()) % serverCount;
            server = upList.get(index);
            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return server;
            }

            server = null;
            Thread.yield();
        }
        return server;

    }
    
    @Override
    public Server choose(Object key) {
        logger.info("choose execute!");
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // ignore
    }
}
