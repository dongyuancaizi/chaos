PostgreSQL 9.5安装
1、添加RPM
yum install https://download.postgresql.org/pub/repos/yum/9.5/redhat/rhel-7-x86_64/pgdg-centos95-9.5-2.noarch.rpm
2、安装PostgreSQL 9.5
yum install postgresql95-server postgresql95-contrib
3、初始化数据库
/usr/pgsql-9.5/bin/postgresql95-setup initdb
4、设置开机自启动
systemctl enable postgresql-9.5.service
5、启动服务
systemctl start postgresql-9.5.service
6、查看版本
psql --version
PostgreSQL 9.5安装配置
1、修改用户密码
su - postgrespsql -U postgresALTER USER postgres WITH PASSWORD '123456'\q
2、开启远程访问
vi /var/lib/pgsql/9.5/data/postgresql.conf修改#listen_addresses = 'localhost'  为  listen_addresses='*'
3、信任远程连接
vi /var/lib/pgsql/9.5/data/pg_hba.conf
修改如下内容，信任指定服务器连接
# IPv4 local connections:
host    all            all      127.0.0.1/32      ident
host    all            all      192.168.137.1/32（需要连接的服务器IP）  trust
4、重启服务
systemctl restart postgresql-9.5.service
