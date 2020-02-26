spring:
  datasource:
    url: jdbc:mysql://47.111.6.183:3306/hey?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password: panpan
  redis:
    database: 0
    host: 47.111.6.183
    password: null
  dubbo:
    registry:
      address: zookeeper://127.0.0.1:2181
    consumer:
      check: false
  rabbitmq:
    addresses: 127.0.0.1
    username: guest
    password: guest
