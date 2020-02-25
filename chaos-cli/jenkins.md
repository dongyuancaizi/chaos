安装
yum install -y java-1.8.0-openjdk
wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat/jenkins.repo #wget -O 
cat /etc/yum.repos.d/jenkins.repo
yum install -y jenkins

启动
systemctl start jenkins

验证
ps aux |grep jenkins
浏览器访问ip:8080
