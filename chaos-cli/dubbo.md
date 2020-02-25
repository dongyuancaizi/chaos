#Dubbo
***
## 快速使用
### 命令
```
telnet 192.168.18.211 20880
ls
ls -I com.test.DemoService
#调用服务接口
invoke com.test.DemoService.queryDemoPageList({"id":"100"},1,2)
```

