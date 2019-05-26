# Spring Cloud
Spring Cloud是一个基于Spring Boot实现的云应用开发工具，它为基于JVM的云应用开发中涉及的配置管理（configuration management），服务发现（servicediscovery）， 断路器（circuit breakers），智能路由（ intelligent routing），微代理（micro-proxy），控制总线（control bus），一次性令牌（ one-time tokens），全局锁（global locks），领导选举（leadership election），分布式会话(distributed sessions），集群状态（cluster state)等操作提供了一种简单的开发方式。
    
# 微服务架构
微服务架构就是将一个完整的应用从数据存储开始垂直拆分成多个不同的服务，每个服务都能独立部署、独立维护、独立扩展，服务与服务间通过诸如RESTful API的方式互相调用。
    
# 服务治理
由于Spring Cloud为服务治理做了一层抽象接口，所以在Spring Cloud应用中可以支持多种不同的服务治理框架，比如：Netflix Eureka、Consul、Zookeeper。在Spring Cloud服务治理抽象层的作用下，我们可以无缝地切换服务治理实现，并且不影响任何其他的服务注册、服务发现、服务调用等逻辑。
    
# Spring Cloud 集成的组件
![spring cloud](src/main/resources/images/spring-cloud.png)  
    
# spring boot 和spring cloud版本对应关系
spring cloud 的Finchley版本对应spring boot的2.0.x版本
    
spring cloud 的Dalston版本对应spring boot的1.5.x版本