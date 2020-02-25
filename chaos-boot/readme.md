[![Build Status](https://travis-ci.org/zhoutaoo/SpringCloud.svg?branch=master)](https://travis-ci.org/zhoutaoo/SpringCloud)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![codecov](https://codecov.io/gh/zhoutaoo/SpringCloud/branch/master/graph/badge.svg)](https://codecov.io/gh/zhoutaoo/SpringCloud)
# [chaos-boot](https://github.com/cui-stack/chaos/)
##框架集成

|  服务     | 使用技术                 |   进度        |    备注   |
|----------|-------------------------|---------------|-----------|
|  基础框架 | springboot              |   ✅          |           |
|  服务调用 | dubbo                   |   ✅          |           |
|  注册中心 | zookeeper               |   ✅          |           |
|  数据映射 | mybatisplus             |   ✅          |           |
|  数据缓存 | redis                   |   ✅          |           |
|  Api管理  | swagger2                |   ✅          |           |
|  配置中心 | nacos                   |   🏗          |           |
|  消息总线 | Rabbitmq                |   ✅          |           |
|  授权认证 | Spring Security OAuth2  |   🏗          |Jwt+redis  |
|  任务调度 | xxl-Job                 |   ✅          |           |
|  对象存储 | OSS                     |   🏗          |           |
|  灰度分流 | OpenResty + lua         |   🏗          |           |
|  动态网关 | SpringCloud Gateway     |   🏗          |  流量控制  |
|  服务容错 | SpringCloud Sentinel    |   🏗          |           |
|  分库分表 | shading-jdbc            |   🏗          |           |

##监控告警

|  服务     | 使用技术                 |   进度        |    备注   |
|----------|-------------------------|---------------|-----------|
|  基础框架 | actuator            |   ✅          | 添加生产准备特性     |
|  服务监控 | springboot-admin    |   ✅          |                     |
|  监控告警 | prometheus          |   🏗          |开源监控告警解决方案   |
|  数据可视 | grafana             |   🏗          |大规模指标数据的可视化 |
|  链路追踪 | zipkin              |   ✅          |链路追踪              |

##其他

##
com.cui.tech.chaos.lite
依赖安装mysql,redis,zookeeper
安全方案
1,管理页面需要登录
2,@UserLoginToken 接口需要带token



consul:(替换eureka)需单独安装,服务网格（微服务间的 TCP/IP，负责服务之间的网络调用、限流、熔断和监控）解决方案
kong?(kong=OpenResty+Nginx+Lua):API网关
hateoas:对基于HATEOAS的RESTful服务的支持 ?
elk


