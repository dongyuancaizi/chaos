app:
  wxmini:
    appid: 88888888
    secret: 88888888
  tencent:
    sms:
      appid: 88888888
      appkey: 88888888
      smsSign:
server:
  port: 28089
mybatis-plus:
  log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
logging:
  level:
    root: INFO
    com:
      favorites: DEBUG
    org:
      hibernate: ERROR
      springframework:
        web: INFO
spring:
  application:
    name: ${package.ModuleName}-service
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    druid:
      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
      filters: stat,wall
      initial-size: 5
      maxActive: 20
      maxPoolPreparedStatementPerConnectionSize: -1
      maxWait: 60000
      min-idle: 5
      minEvictableIdleTimeMillis: 300000
      poolPreparedStatements: true
      stat-view-servlet:
        allow: 127.0.0.1
        deny: ''
        enabled: true
        login-password: admin
        login-username: admin
        reset-enable: false
        url-pattern: /druid/*
      testOnBorrow: true
      testOnReturn: false
      testWhileIdle: true
      timeBetweenEvictionRunsMillis: 60000
      validationQuery: SELECT 1 FROM DUAL
      web-stat-filter:
        enabled: true
        exclusions: '*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*'
        url-pattern: /*
    type: com.alibaba.druid.pool.DruidDataSource
  boot:
    admin:
      client:
        url: 'http://39.100.194.142:8000'
        instance:
          prefer-ip: true
  dubbo:
    application:
      name: ${package.ModuleName}-service
    server: true
    protocol:
      name: dubbo
      port: 28089
    consumer:
      check: false
    reference:
      loadbalance: random #随机机制
  redis:
    jedis:
      pool:
        max-active: 200
        maxIdle: 10
        minIdle: 1
    port: 6379
    timeout: 2000
management:
  health:
    mongo:
      enabled: false
    rabbit:
      enabled: false
  endpoint:
    health:
      show-details: always
  endpoints:
    web:
      exposure:
        include: '*'
xxl:
  job:
    accessToken: ''
    admin:
      addresses: http://39.100.194.142:19999/xxl-job-admin
    executor:
      appname: xxl-job-executor-sample
      ip: ''
      logpath: /data/applogs/xxl-job/jobhandler
      logretentiondays: 30
      port: 9992
zipkin:
  compressionEnabled: true
  connectTimeout: 6000
  flushInterval: 1
  readTimeout: 6000
  serviceName: ${package.ModuleName}-service
  url: http://39.100.194.142:9411
