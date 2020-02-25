<template>
  <div>
    <div class="login-wrap" v-show="showLogin">
      <h3>登录</h3>
      <p v-show="showTishi">{{tishi}}</p>
      <input type="text" placeholder="请输入用户名" v-model="username">
      <input type="password" placeholder="请输入密码" v-model="password">
      <button v-on:click="login">登录</button>
      <span v-on:click="register">没有账号？马上注册</span>
    </div>

    <div class="register-wrap" v-show="showRegister">
      <h3>注册</h3>
      <p v-show="showTishi">{{tishi}}</p>
      <input type="text" placeholder="请输入用户名" v-model="newUsername">
      <input type="password" placeholder="请输入密码" v-model="newPassword">
      <button v-on:click="register">注册</button>
      <span v-on:click="login">已有账号？马上登录</span>
    </div>
  </div>
</template>

<style>
  .login-wrap {
    text-align: center;
  }

  input {
    display: block;
    width: 250px;
    height: 40px;
    line-height: 40px;
    margin: 0 auto;
    margin-bottom: 10px;
    outline: none;
    border: 1px solid #888;
    padding: 10px;
    box-sizing: border-box;
  }

  p {
    color: red;
  }

  button {
    display: block;
    width: 250px;
    height: 40px;
    line-height: 40px;
    margin: 0 auto;
    border: none;
    background-color: #41b883;
    color: #fff;
    font-size: 16px;
    margin-bottom: 5px;
  }

  span {
    cursor: pointer;
  }

  span:hover {
    color: #41b883;
  }
</style>

<script>
    //import {setCookie, getCookie} from '../../assets/js/cookie.js'
    import fetch from '../../axios/fetch'
    import store from '../../vuex/store'
    export default {
        store,
        mounted() {
        },
        methods: {
            login() {
                if (this.username == "" || this.password == "") {
                    alert("请输入用户名或密码")
                } else {
                    const data = {
                        'username': this.username,
                        'password': this.password
                    }
                    /*接口请求*/
                    fetch.post('/web/login', data).then((res) => {
                        /*接口的传值是(-1,该用户不存在),(0,密码错误)，同时还会检测管理员账号的值*/
                        if (res.code == 500) {
                            this.tishi = "该用户不存在"
                            this.showTishi = true
                        } else if (res.code == 500) {
                            this.tishi = "密码输入错误"
                            this.showTishi = true
                        } else if (res.data == 'admin') {
                            /*路由跳转this.$router.push*/
                            this.$router.push('/main')
                        } else if (res.code == 200) {
                            this.tishi = "登录成功"
                            this.showTishi = true;
                            this.$store.commit('SetUserinfo',res.data)
                            setTimeout(function () {
                                this.$router.push('/main')
                            }.bind(this), 100)
                        }
                    }, (reason) => {
                        console.log(reason)
                    })
                }
            },
            register() {
            }
        },
        data() {
            return {
                showLogin: true,
                showRegister: false,
                showTishi: false,
                tishi: '',
                username: '',
                password: '',
                newUsername: '',
                newPassword: ''
            }
        }
    }
</script>
