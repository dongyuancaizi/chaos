安装
yum -y install gcc zlib zlib-devel pcre-devel openssl openssl-devel
./configure
make install
命令
cd  /usr/local/nginx/sbin/
nginx
nginx  -s stop
nginx  -s reload
