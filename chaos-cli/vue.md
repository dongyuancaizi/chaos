# Vue全家桶
vue-cli + vue2.0 + vuex + vue-router + axios + element-ui
***
## 快速使用
### 环境
* nodejs
    - [官网](https://nodejs.org/) 
    - [下载](https://nodejs.org/download/)
    - 验证 `node -v npm -v`
* cnpm
```
npm install -g cnpm --registry=http://registry.npm.taobao.org
```
* yarn
```
cnpm install -g yarn
yarn config set registry https://registry.npm.taobao.org -g
yarn config set sass_binary_site http://cdn.npm.taobao.org/dist/node-sass -g
```
### 安装
```
#vue-cli
cnpm install -g vue-cli
#初始化项目
vue init webpack x
#运行
cd x
npm run dev
#打包
npm run build
```
### 其他
```
#安装插件
cnpm install vue-router --save
cnpm install vuex --save
cnpm install axios --save
cnpm i element-ui -S
cnpm install babel-plugin-component -D
cnpm install vue-wechat-title --save
```







