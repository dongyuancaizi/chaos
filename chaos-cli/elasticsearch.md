@windows
5.5.0版本
下载
wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-6.4.2.zip
unzip elasticsearch-6.4.2.zip
修改配置
config/elasticsearch.yml  
network.host: 0.0.0.0
安装
adduser  xiaobao
passwd xiaobao
cuijian99
chown xiaobao  elasticsearch-5.5.0 -R
安装插件
./bin/elasticsearch-plugin install x-pack
验证
curl 192.168.18.210:9200
异常解决
sudo sysctl -w vm.max_map_count=262144
/etc/security/limits.conf 
启动
su xiaobao
./bin/elasticsearch
后台启动
nohup bin/elasticsearch &
bin/elasticsearch -d
移除插件
bin/elasticsearch-plugin remove x-pack
