#docker
## @windows
[下载]()DockerToolbox-18.03.0-ce.exe
1. 启动 Docker Quickstart Terminal
2. 断网 boot2docker.iso C:\Users\cjj_a\.docker\machine\cache
3. 配置加速
4. http https 问题
```
1.启动 Docker Quickstart
2.进入default   
docker-machine ssh default
3.切换到root 用户，因为docker 用户访问配置文件为制度
sudo -i
4.修改文件 /var/lib/boot2docker/profile 文件中的 EXTRA_ARGS 信息，向其中加入对应的访问路径
vi /var/lib/boot2docker/profile
--insecure-registry 39.100.194.142:8288
docker-machine restart
192.168.99.100
docker
tcuser
```
## @centos
### 安装
```
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
### 命令
```
docker pull library/hello-world
docker images
docker rmi
docker run hello-world
docker ps -a
docker inspect 容器id 查询容器信息
docker exec -it 容器id/容器名 bash   (进去容器内部)    exit(退出)
docker stop 容器id 停止容器id
docker rm container_name/container_id
docker rm $(docker ps -a -q) 删除所有停止的容器
docker search nginx
systemctl daemon-reload
systemctl restart docker
docker system df
docker system prune
docker attach container_name/container_id
docker volume rm $(docker volume ls -q)
du -sh
```
du -h --max-depth=1 | sort

netstat -tulp

apt-get update
apt-get install vim
apt-get install lrzsz
