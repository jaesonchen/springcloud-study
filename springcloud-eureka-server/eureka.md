# eureka
Eureka是Netflix开源的一款提供服务注册和发现的产品，它提供了完整的Service Registry和Service Discovery实现。也是springcloud体系中最重要最核心的组件之一。
    

## @EnableEurekaServer
注解@EnableEurekaServer用于启动eureka 注册中心。
    
## eureka server配置
```
// eureka服务端口
server.port = 8000
// eureka服务主机名，也可以不配置，默认将会使用IP进行查找。
eureka.instance.hostname = localhost
// 是否将自己注册到Eureka Server，默认为true。
eureka.client.register-with-eureka = false
// 是否从Eureka Server获取注册信息，默认为true。
eureka.client.fetch-registry = false
// Eureka Server交互地址，注册和发现服务都需要依赖这个地址。
eureka.client.serviceUrl.defaultZone = http://${eureka.instance.hostname}:${server.port}/eureka/
```
    
    
## @EnableDiscoveryClient与@EnableEurekaClient
Spring Cloud 中eureka注册中心的服务发现可以使用两种注解, 一种为@EnableDiscoveryClient, 一种为@EnableEurekaClient。
    
spring cloud中discovery service有许多种实现（eureka、consul、zookeeper等等），@EnableDiscoveryClient基于spring-cloud-commons, @EnableEurekaClient基于spring-cloud-netflix。
    
其实用更简单的话来说，就是如果选用的注册中心是eureka，那么就推荐@EnableEurekaClient，如果是其他的注册中心，那么推荐使用@EnableDiscoveryClient。
    

## eureka client 配置
```
// 需要配置eureka注册中心的地址，多个eureka用逗号分隔
eureka.client.serviceUrl.defaultZone = http://localhost:8000/eureka/
// spring应用名称，注册到eureka时会被当作服务名称，consumer消费时需要指定该名称
spring.application.name = service-hello
```
    
## Eureka的自我保护机制
自我保护模式正是一种针对网络异常波动的安全保护措施，使用自我保护模式能使Eureka集群更加的健壮、稳定的运行。
    
自我保护机制的工作机制是如果在15分钟内超过85%的客户端节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，Eureka Server自动进入自我保护机制，此时会出现以下几种情况：    
- Eureka Server不再从注册列表中移除因为长时间没收到心跳而应该过期的服务。
- Eureka Server仍然能够接受新服务的注册和查询请求，但是不会被同步到其它节点上，保证当前节点依然可用。
- 当网络稳定时，当前Eureka Server新的注册信息会被同步到其它节点中。
    
## 自我保护机制的相关配置
**服务端的配置**：
    
```
eureka.server.enable-self-preservation: 该配置可以移除这种自我保护机制，防止失效的服务也被一直访问 (默认是 true)。
eureka.server.eviction-interval-timer-in-ms: 该配置可以修改检查失效服务的时间，每隔10s检查失效服务，并移除列表 (默认是 60s)
```
**客户端的配置**:
    
```
eureka.instance.lease-renewal-interval-in-seconds: 该配置指示eureka客户端需要向eureka服务器发送心跳的频率 (Spring Cloud默认该配置是 30s)。
eureka.instance.lease-expiration-duration-in-seconds: 该配置指示eureka服务器在接收到最后一个心跳之后的等待时间，然后才能从列表中删除此实例 (默认是 90s)。
```
    
## Eureka的的心跳机制
Eureka Client需要每30秒给Eureka Server发一次心跳，同时更新Server上最新的注册信息到本地，如果Server多次没有收到来自客户端的心跳，那么在90秒内会被Server上剔除。
    
    
# 高可用的注册中心
作为分布式系统最重要的注册服务功能，为了使其高可用，使用集群是最普遍的方式。Eureka可以通过互相注册的方式来实现高可用的部署。
    
## 节点一
```
server.port = 8010
eureka.instance.hostname = peer1
// 多个节点可以用逗号分隔
eureka.client.serviceUrl.defaultZone = http://peer2:8020/eureka/
```
    
## 节点二
```
server.port = 8020
eureka.instance.hostname = peer2
eureka.client.serviceUrl.defaultZone = http://peer1:8010/eureka/
```
    
## 服务注册与发现
```
eureka.client.serviceUrl.defaultZone = http://peer1:8010/eureka/, http://peer2:8020/eureka/
```
