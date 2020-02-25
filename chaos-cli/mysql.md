mysql
rpm -qa | grep mysql
rpm -e mysql　　// 普通删除模式
rpm -e --nodeps mysql　　// 强力删除模式，如果使用上面命令删除时，提示有依赖的其它文件，则用该命令可以对其进行强力删除
yum install mariadb-server mariadb
systemctl start mariadb #启动MariaDB
systemctl stop mariadb #停止MariaDB
systemctl restart mariadb #重启MariaDB
systemctl enable mariadb #设置开机启动
查看版本
mysql -V
mysql  Ver 15.1 Distrib 5.5.60-MariaDB, for Linux (x86_64) using readline 5.1
mysqladmin --version
mysql //linux访问mysql
SHOW DATABASES;
mysqladmin -u root password "panpan"; //设置密码
mysql -u root -p
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'panpan' WITH GRANT OPTION; // %:表示从任何主机连接到mysql服务器
FLUSH   PRIVILEGES;
解决外网访问
select user,authentication_string,host from user; (密码字段改了)
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%'IDENTIFIED BY 'WEIYI2018' WITH GRANT OPTION;
查看
show profile
explain
死锁
show processlist;
kill10866


SELECT @@sql_mode;
SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
