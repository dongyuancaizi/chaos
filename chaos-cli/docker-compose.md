# docker-compose
@centos
```
yum -y install -y docker-compose  &&  yum install bash-completion && docker-compose -v
```
```
docker-compose up -d
docker-compose scale web=3 db=2
docker-compose ps |stop |logs |buil
```
