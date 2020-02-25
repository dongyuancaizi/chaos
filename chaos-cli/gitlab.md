gitlab使用
安装gitlab插件
使用ssh-key命令 生成公钥，私钥 .ssh文件中 使用cai命令获取
gitlab中ssh-key 增加ssh-rsa 

cat /etc/redhat-release
yum update -y
yum install wget -y
wget https://mirrors.tuna.tsinghua.edu.cn/gitlab-ce/yum/el7/gitlab-ce-11.4.6-ce.0.el7.x86_64.rpm
yum install policycoreutils-python
rpm -ivh gitlab-ce-11.4.6-ce.0.el7.x86_64.rpm
grep "^external_url" /etc/gitlab/gitlab.rb
vim
external_url 'http://47.111.6.183'
nginx['listen_port']=9091
unicorn['port']=9092
gitlab-ctl reconfigure
gitlab-ctl restart
netstat -tlnp
gitlab-ctl status
http://47.111.6.183
关闭
sudo gitlab-ctl start/stop
#Title
***
## 快速使用
### 环境
*
*
*
### 安装
1.
2.
3.
### 命令
```

```
### 其他
[账号找回](https://docs.gitlab.com/ce/security/reset_root_password.html)
```
su - git
gitlab-rails console -e production
user = User.where(id: 1).first
user.password = '12345678'
user.password_confirmation = '12345678'
user.save!
exit
root/12345678
```


centos-gitlab卸载
gitlab-ctl stop
rpm -e gitlab-ce
ps aux | grep gitlab
kill -9 18777 #（18777 是第一个进程的pid号，根据显示情况输入）
ps -ef | grep gitlab | xargs kill -s 9
find / -name gitlab | xargs rm -rf


sudo docker pull gitlab/gitlab-ce:latest
sudo docker run -d \ --hostname gitlab.xxx.com \ --publish 8443:443 --publish 18181:80 --publish 18122:22 \ --name gitlab \ --restart always \ --volume /mnt/data0/gitlab/config:/etc/gitlab \ --volume /mnt/data0/gitlab/logs:/var/log/gitlab \ --volume /mnt/data0/gitlab/data:/var/opt/gitlab \ gitlab/gitlab-ce:latest
http://127.0.0.1:18181
汉化(版本必须一致)
`wget https://gitlab.com/xhang/gitl... -O gitlab-12-0-stable-zh.tar.bz2
tar xf gitlab-12-0-stable-zh.tar.bz2
cat gitlab-12-3-stable-zh/VERSION
docker run -t -i gitlab/gitlab-ce:latest /bin/bash
docker cp gitlab-12-0-stable-zh.tar.bz2 ${容器id}:/etc/gitlab
docker commit -m "added zh" -a "user" ${容器id} gitlab/gitlab-ce:12-0-zh
sudo docker run -d \ --hostname gitlab.xxx.com \ --publish 8443:443 --publish 18181:80 --publish 18122:22 \ --name gitlab \ --restart always \ --volume /mnt/data0/gitlab/config:/etc/gitlab \ --volume /mnt/data0/gitlab/logs:/var/log/gitlab \ --volume /mnt/data0/gitlab/data:/var/opt/gitlab \ gitlab/gitlab-ce:12-0-zh
