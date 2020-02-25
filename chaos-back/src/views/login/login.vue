<template>
  <div class="login-container">
    <div class="login-head">
      <img src="../../assets/images/logo.png">
      <span style="font-size:35px;padding-bottom: 10px">矮芽</span>
    </div>
    <div class="login-area">
      <h2>后台登录</h2>
      <p v-show="showTishi">{{tishi}}</p>
      <input type="text" placeholder="请输入用户名" v-model="username">
      <input type="password" placeholder="请输入密码" v-model="password">
      <button v-on:click="login">登录</button>
    </div>

  </div>
</template>

<script>
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
                    fetch.post('/manage/login', data).then((res) => {
                        if (res.code == 500) {
                            this.tishi = "该用户不存在"
                            this.showTishi = true
                        } else if (res.code == 200) {
                            this.tishi = "登录成功"
                            this.showTishi = true;
                            store.dispatch('setUserinfo', res.data)
                            setTimeout(function () {
                                this.$router.push('/main')
                            }.bind(this), 100)
                        }
                    }, (reason) => {
                        console.log(reason)
                    })
                }
            },
        },
        data() {
            return {
                showTishi: false,
                tishi: '',
                username: '',
                password: ''
            }
        }
    }
</script>

<style scoped>

  .login-container {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    overflow-y: auto;
    background-color: #ff746e;
    font-family: "宋体";
    color: white;
    text-align: center;
  }

  .login-head {
    margin-top: 50px;
  }

  .login-area {
    text-align: center;
    background-color: white;
    color: #ff746e;
    width: 360px;
    height: 260px;
    margin: 0 auto;
  }

  .login-area h2 {
    padding-top: 20px;
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
  }

  span {
    cursor: pointer;
  }

  span:hover {
    color: #41b883;
  }
</style>
