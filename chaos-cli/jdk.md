#Jdk
[官网](https://www.java.com/zh_CN/)
***
## 快速使用
### @windows
[下载](https://www.java.com/zh_CN/download/windows-64bit.jsp)
```
JAVA_HOME =C:\Java\jdk1.8.0_171
CLASSPAT=.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar
Path=%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;
```
### 验证
<kbd>Win</kbd>+<kbd>R</kbd>
```
java -version
```

###  @centos
### 安装
```
yum install lrzsz | vim
mkdir /usr/java
cd /usr/java
tar -zxvf jdk-8u161-linux-x64.tar.gz
```
profile
```
vim /etc/profile
export JAVA_HOME=/usr/java/jdk1.8.0_161
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
source /etc/profile
```
```
java -version
```
