#Redis
***
## 快速使用
### 环境
*
*
*
### 安装
```
wget http://download.redis.io/releases/redis-4.0.11.tar.gz
tar xzf redis-4.0.11.tar.gz
yum -y install gcc
cd redis-4.0.11
make
```
修改配置
```
vim redis.conf
daemonize yes
dir /app/redis/redis-4.0.11/bin
appendonly yes
logfile "/app/log/redis.log"
#bind 127.0.0.1
protected-mode no
```
### 命令
```
./redis-server redis.conf
./src/redis-cli
后台启动
nohup /usr/local/redis/redis-stable/src/redis-server /etc/redis.conf &
nohup /usr/local/redis-4.0.8/src/redis-server /usr/local/redis/etc/redis.conf &
192.168.18.220
nohup /usr/local/redis-4.0.11/src/redis-server /usr/local/redis-4.0.11/redis.conf &
关闭
nohup /data/redis/bin/redis-cli -p 6379 -a Cb201609(redis密码) shutdown &
删库
FLUSHALL
```
### 其他


