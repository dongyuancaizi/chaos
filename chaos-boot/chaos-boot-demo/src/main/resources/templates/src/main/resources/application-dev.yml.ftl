spring:
  datasource:
    url: jdbc:mysql://39.100.194.142:3306/hey?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password: root
  redis:
    database: 0
    host: 39.100.194.142
    password: null
  dubbo:
    registry:
      address: zookeeper://39.100.194.142:2181
    consumer:
      check: false
  rabbitmq:
    addresses: 39.100.194.142
    username: guest
    password: guest
