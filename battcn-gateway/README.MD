`spring.gateway.discovery.locator.enabled` 设置成 `true` 的配置

``` yaml
spring:
    gateway:
      discovery:
        locator:
          # 配置成 true 那么 Path 就应该是 battcn-order or battcn-auth 即是设置可以路由到其他服务
          # 如果觉得 install-id 比较长看起来不舒服，可以用 StripPrefix 清洗的方式而不是 locator.enabled = true
          enabled: true
      routes:
        - id: battcn-order
          uri: lb://battcn-order
          predicates:
            - Path=/battcn-order/**
          # 配置 StripPrefix 清洗
          filters:
            - StripPrefix=1
        - id: battcn-auth
          uri: lb://battcn-auth
          predicates:
            - Path=/battcn-auth/**
          filters:
            - StripPrefix=1
```

`spring.gateway.discovery.locator.enabled` 设置成 `false` 的配置（默认就是 `false`）

``` yaml
spring:
    gateway:
      routes:
        - id: battcn-order
          uri: lb://battcn-order
          predicates:
            - Path=/order/**
          # 配置 StripPrefix 清洗
          filters:
            - StripPrefix=1
        - id: battcn-auth
          uri: lb://battcn-auth
          predicates:
            - Path=/auth/**
          filters:
            - StripPrefix=1
```

前者可以根据 `install-id` 进行路由转发，后者是可以根据某些特殊路径进行过滤转发，具体可以结合自己公司应用场景去扩展