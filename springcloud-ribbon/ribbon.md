# Ribbon
Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具。它是一个基于HTTP和TCP的客户端负载均衡器。它可以通过在客户端中配置ribbonServerList来设置服务端列表去轮询访问以达到均衡负载的作用。
    
当Ribbon与Eureka联合使用时，ribbonServerList会被DiscoveryEnabledNIWSServerList重写，扩展成从Eureka注册中心中获取服务实例列表。同时它也会用NIWSDiscoveryPing来取代IPing，它将职责委托给Eureka来确定服务端是否已经启动。
    
而当Ribbon与Consul联合使用时，ribbonServerList会被ConsulServerList来扩展成从Consul获取服务实例列表。同时由ConsulPing来作为IPing接口的实现。
    
我们在使用Spring Cloud Ribbon的时候，不论是与Eureka还是Consul结合，都会在引入Spring Cloud Eureka或Spring Cloud Consul依赖的时候通过自动化配置来加载上述所说的配置内容，所以我们可以快速在Spring Cloud中实现服务间调用的负载均衡。
    
![springcloud ribbon](src/main/resources/images/springcloud-ribbon.png)  
    

## @LoadBalanced
@LoadBalanced 为RestTemplate提供负载均衡。
    
## LoadBalancerClient
也可以通过LoadBalancerClient.choose("service-hello")来手动调用负载均衡算法得到当前的调用地址。
    

## 自定义负载均衡实现
可以自定义负载均衡算法，需要实现IRule接口，通常extends AbstractLoadBalancerRule 重写choose方法即可。
    
```
public class MyLoadBalancerRule extends AbstractLoadBalancerRule {
    @Override
    public Server choose(Object key) {
        logger.info("choose execute!");
        return choose(getLoadBalancer(), key);
    }
    private Server choose(ILoadBalancer lb, Object key) {
        ...
    }
}
```
    