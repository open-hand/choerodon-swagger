# hzero-swagger

swagger服务，api文档管理服务


## 特性

- API文档查看
- 服务API文档刷新
- API调试

## 功能介绍

1. 接口管理：
    - API文档查看、API调试功能

## 服务配置

- `application.yml`

  ```yaml
    spring:
      application:
        name: hzero-swagger
      datasource:
        url: ${SPRING_DATASOURCE_URL:jdbc:mysql://172.23.16.45:3306/hzero_admin?useUnicode=true&characterEncoding=utf-8&useSSL=false}
        username: ${SPRING_DATASOURCE_USERNAME:hzero}
        password: ${SPRING_DATASOURCE_PASSWORD:hzero}
        hikari:
          # 连接池最小空闲连接数
          minimum-idle: ${SPRING_DATASOURCE_MINIMUM_IDLE:20}
          # 连接池允许的最大连接数
          maximum-pool-size: ${SPRING_DATASOURCE_MAXIMUM_POOL_SIZE:200}
          # 等待连接池分配连接的最大时长（毫秒）
          connection-timeout: ${SPRING_DATASOURCE_CONNECTION_TIMEOUT:30000}
      redis:
        host: ${SPRING_REDIS_HOST:redis.hzero.org}
        port: ${SPRING_REDIS_PORT:6379}
        database: ${SPRING_REDIS_DATABASE:1}
        jedis:
          pool:
            # 资源池中最大连接数
            # 默认8，-1表示无限制；可根据服务并发redis情况及服务端的支持上限调整
            max-active: ${SPRING_REDIS_POOL_MAX_ACTIVE:50}
            # 资源池运行最大空闲的连接数
            # 默认8，-1表示无限制；可根据服务并发redis情况及服务端的支持上限调整，一般建议和max-active保持一致，避免资源伸缩带来的开销
            max-idle: ${SPRING_REDIS_POOL_MAX_IDLE:50}
            # 当资源池连接用尽后，调用者的最大等待时间(单位为毫秒)
            # 默认 -1 表示永不超时，设置5秒
            max-wait: ${SPRING_REDIS_POOL_MAX_WAIT:5000}
    
    feign:
      hystrix:
        enabled: true
    
    hystrix:
      stream:
        queue:
          enabled: true
      command:
        default:
          execution:
            timeout:
              enabled: true
            isolation:
              thread:
                timeoutInMilliseconds: ${HYSTRIX_COMMAND_TIMEOUT_IN_MILLISECONDS:90000}
    
    mybatis:
      mapperLocations: classpath*:/mapper/*.xml
      configuration:
        mapUnderscoreToCamelCase: true
    
    hzero:
      swagger:
        client: ${HZERO_SWAGGER_CLIENT:client}
        skip-service: ${HZERO_SWAGGER_SKIP_SERVICE:register, gateway, oauth}
        enable-https: ${HZERO_SWAGGER_ENABLE_HTTPS:false}
        fetch-time: ${HZERO_SWAGGER_FETCH_TIME:20}
        fetch-seconds: ${HZERO_SWAGGER_FETCH_SECONDS:30}
    logging:
      level:
        org.hzero: ${LOG_LEVEL:info}
        io.choerodon: ${LOG_LEVEL:info}
        org.springframework.web: ${LOG_LEVEL:info}
      path: ./logs
    


  ```

- `bootstrap.yml`

  ```yaml
  server:
    port: 8050
  management:
    server:
      port: 8051
  
  
  eureka:
    instance:
      # 以IP注册到注册中心
      preferIpAddress: ${EUREKA_INSTANCE_PREFER_IP_ADDRESS:true}
      leaseRenewalIntervalInSeconds: 10
      leaseExpirationDurationInSeconds: 30
      # 服务的一些元数据信息
      metadata-map:
        VERSION: 1.3.1.RELEASE
    client:
      serviceUrl:
        # 注册中心地址
        defaultZone: ${EUREKA_DEFAULT_ZONE:http://dev.hzero.org:8000/eureka}
      registryFetchIntervalSeconds: 10
      disable-delta: true

  ```

## 环境需求

- mysql 5.7+
- redis 3.0+
- `hzero-swagger` 服务依赖于 hzero-admin 服务的数据库, 所以请确保 `hzero-admin` 服务的数据库已经创建并初始化。
- 该项目是一个 Eureka Client 项目，启动后需要注册到 `hzero-register`

## 安装和启动步骤

- 运行 `hzero-register`

- 本地启动 redis-server

- 启动项目，项目根目录下执行如下命令：

  ```sh
   mvn spring-boot:run
  ```

## 更新日志

- [更新日志](./CHANGELOG.zh-CN.md)

## 如何参与

- 欢迎参与我们的项目，了解更多有关如何[参与贡献](https://github.com/choerodon/choerodon/blob/master/CONTRIBUTING.md)的信息。


