FROM java:8

#作者
MAINTAINER cui

# 注意 此处 EVN 设置注册中心地址
#ENV DUBBO_IP_TO_REGISTRY 172.19.165.222   #服务名称/IP地址
#ENV DUBBO_PORT_TO_REGISTRY 12345      #端口（默认不需要修改端口）

#挂载点
VOLUME /tmp

ADD dubbo-admin-distribution/target/dubbo-admin-0.1.jar app.jar
#命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
