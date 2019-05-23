package com.asiainfo.springcloud.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**   
 * @Description: TODO
 * 
 * @author chenzq  
 * @date 2019年5月21日 上午10:02:37
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableTurbine
public class TurbineApplication {

    // http://localhost:9050/hystrix
    // http://localhost:9050/turbine.stream
    // http://localhost:9050/turbine.stream?cluster=[clusterName]
    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class, args);
    }
}
