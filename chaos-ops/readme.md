# chaos-ops
## 基于阿里云ecs的运维部署方案
|  服务     | 使用技术       |   进度        |    环境    |    方式   |    版本   |
|----------|----------------|---------------|------------|-----------|-----------|
|  容器管理 | rancher        |   ✅          | BOSS        |docker    |  1.6.29  |
|  代码生成 | chaos-code     |   ✅          | BOSS        |compose   |          |
|  代码仓库 | gitlab         |   ✅          | BOSS        |docker    |          |
|  构件仓库 | nexus          |   ✅          | BOSS        |compose   |     3    |
|  镜像仓库 | harbor         |   ✅          | BOSS        |compose   |  1.9.4   |
|  CI执行器 | gitlab-runner  |   ✅          | BOSS        |docker    |          |
|  注册中心 | zookeeper      |   ✅          |BOSS/Common  |compose   |          |
|   数据库  | mysql          |   ✅          |BOSS/Common  |compose   |          |
|  KV数据库 | redis          |   ✅          |BOSS/Common  |compose   |          |
|  消息总线 | rabbitmq       |   ✅          |BOSS/Common  |compose   |          |
|  任务管理 | xxljob         |   ✅          |BOSS/Common  |compose   |          |
|  链路追踪 | zipkin         |   ✅          |BOSS/Common  |compose   |          |
|  RPC服务  | dubbo-admin    |   ✅          |BOSS/Common  |compose   |          |
| 微服务管理| chaos-admin    |   ✅          |BOSS/Common  |compose   |          |
|  代理服务 | nginx          |   ✅          |BOSS/Common  |compose   |          |

### 快速开始 
* install os -> @centos boss
```text
base on centos 7.6
```
```shell script
yum -y install lrzsz
yum -y install unzip zip
cd /usr/local/
mkdir -p nexus/data && chown -R 200 nexus/data
```
* install docker
```shell script
yum -y install yum-utils  device-mapper-persistent-data  lvm2
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum -y install docker-ce docker-ce-cli containerd.io
systemctl  start docker && systemctl  enable docker  &&  docker -v
vim  /etc/docker/daemon.json
{
    "registry-mirrors": ["https://9b3ogh7r.mirror.aliyuncs.com"],
    "insecure-registries":["39.100.194.142:8288"] 
}
systemctl  restart docker
```
* install docker-compose
```shell script
yum -y install -y docker-compose  &&  yum install bash-completion && docker-compose -v
```
* install gitlab
```
gitlab
```
* install gitlab-runner
```shell script
sudo docker run -d --name gitlab-runner --restart always   \
  -v /srv/gitlab-runner/config:/etc/gitlab-runner    \
  -v /var/run/docker.sock:/var/run/docker.sock   \
  -v /root/.m2:/root/.m2                   \
  -v /usr/bin/docker:/usr/bin/docker        \
  gitlab/gitlab-runner:latest

sudo docker exec -it gitlab-runner  gitlab-runner register 
    http://git.okyakid.com/
    ek6g2xz8LgTRTCTug4hG
    maven,docker
    docker
    docker:19.03.1
sudo vim /srv/gitlab-runner/config/config.toml
    privileged = true
    volumes = ["/cache","/certs/client","/root/.m2:/root/.m2","/var/run/docker.sock:/var/run/docker.sock"]

docker restart gitlab-runner
```
* install harbor
```shell script
ca /usr/local/boss
wget https://github.com/goharbor/harbor/releases/download/v1.9.4/harbor-offline-installer-v1.9.4.tgz
tar -zxvf  harbor-offline-installer-v1.9.4.tgz
cd harbor
vim harbor.yml
    hostname: 172.26.218.18
./prepare
docker-compose up -d
```
* install rancher
```docker
rancher:1.6.29
docker run -d -v /user/local/boss/rancher/mysql:/var/lib/mysql \
--restart=unless-stopped -p 8383:8080 rancher/server
rancher:2.2.3
docker run -d -v /user/local/boss/rancher/data:/var/lib/rancher/  \
--restart=unless-stopped --name rancher-server -p 80:80 -p 443:443   \
rancher/rancher:stable    \
```
* @windows local

[docker](https://github.com/cui-stack/chaos/blob/master/chaos-cli/windows/docker.md)

[push-image](https://github.com/cui-stack/chaos/blob/master/chaos-ops/local/push.md)

*run boss @centos boss
```shell script
cd /usr/local/
rz chaos/chaos-ops/boss.zip
unzip boss.zip
cd boss
docker-compose up -d 
```
*config boss @centos boss

[研发中台](http://center.okyakid.com/#/main)
[gitlab]()
[nexus](https://github.com/cui-stack/chaos/blob/master/chaos-ops/local/nexus.md)
[rancher]()
[nacos]()
[xxl-job]()

##维护
```
docker container   stop   $(docker  container  ls   -a  -q)

docker rmi --force $(docker images | grep rancher| awk  '{print $3}')

docker rm $(docker ps -a -q) 删除所有停止的容器
docker system prune -a
docker system prune -f --volumes  清除
docker system df
netstat -tulp
docker rm `docker ps -a | grep Exited | awk '{print $1}'`   删除异常停止的docker容器

docker rmi -f  `docker images | grep '<none>' | awk '{print $3}'`  删除名称或标签为none的镜像
————————————————
```
