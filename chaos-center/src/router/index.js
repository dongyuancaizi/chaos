import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/login/login'
import Home from '../views/head/head'
import Main from '../views/main/main'

Vue.use(Router)


export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      meta: { title: "登录" }
    },
    {
      path: '/main',
      name: 'Main',
      component: Main,
      meta: { title: "首页" }
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      meta: { title: "我的" }
    }
  ]
})
