#Maven
***
## 快速使用
### Base on
* centos
* 
*
### 安装
1. [下载](http://maven.apache.org/download.cgi)
`code`
2. 验证
3.
### 命令
#### 启动
`code`
#### 关闭
`code`
## 其他
#Maven
[下载](http://maven.apache.org/download.cgi)
[Maven库](http://repo2.maven.org/maven2/)
[Maven依赖查询](http://mvnrepository.com/)
***
## 快速使用
### 环境
* echo %JAVA_HOME%  检查环境变量
* JAVA_HOME 
### 安装
```
# 解压 
apache-maven-3.6.3-bin.zip
MAVEN_HOME
PATH %MAVEN_HOME%\bin\
mvn help:system
# 验证
mvn --version
```
### 命令
```
# 创建Maven的普通Java项目
mvn archetype:create    -DgroupId=packageName    -DartifactId=projectName
# 创建Maven的Web项目
mvn archetype:create    -DgroupId=packageName    -DartifactId=webappName    -DarchetypeArtifactId=maven-archetype-webapp
# 反向生成 maven 项目的骨架
mvn archetype:generate
mvn archetype:generate -DgroupId=otowa.user.dao -DartifactId=user-dao -Dversion=0.01-SNAPSHOT
# 编译源代码
mvn compile
# 运行测试
mvn test
# 打包
mvn package
# 安装
mvn install
# 强制安装
mvn clean install -U 
# 清除
mvn clean
# 上传
mvn deploy
```
### 其他
