# Feign
Spring Cloud Feign是一套基于Netflix Feign实现的声明式服务调用客户端。它使得编写Web服务客户端变得更加简单。我们只需要通过创建接口并用注解来配置它既可完成对Web服务接口的绑定。它具备可插拔的注解支持，包括Feign注解、JAX-RS注解。它也支持可插拔的编码器和解码器。Spring Cloud Feign还扩展了对Spring MVC注解的支持，同时还整合了Ribbon和Eureka来提供均衡负载的HTTP客户端实现。
   
- Feign 采用的是基于接口的注解
- Feign 整合了Ribbon，具有负载均衡的能力
- Feign 整合了Hystrix，具有熔断的能力
    
# @EnableFeignClients
@EnableFeignClients用于启用Feign。
    
## @FeignClient
通过Spring Cloud Feign来实现服务调用的方式更加简单了，通过@FeignClient定义的接口来统一的生命我们需要依赖的微服务接口。而在具体使用的时候就跟调用本地方法一点的进行调用即可。由于Feign是基于Ribbon实现的，所以它自带了客户端负载均衡功能，也可以通过Ribbon的IRule进行策略扩展。
    
另外，Feign还整合的Hystrix来实现服务的容错保护，在Dalston版本中，Feign的Hystrix默认是关闭的。
    
可通过配置`feign.hystrix.enabled = true`打开hystrix组件。
    