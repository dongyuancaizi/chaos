import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/login/login'
import Main from '@/views/main/main'
import appointment from '@/views/user/appointment/appointment'
import userlist from '@/views/user/userlist/userlist'
import userbookcount from '@/views/user/userlist/userbookcount'
import uservisitcount from '@/views/user/userlist/uservisitcount'
import searchhistory from '@/views/user/searchhistory/searchhistory'
import operateaccount from '@/views/user/operateaccount/operateaccount'
import addOperateaccount from '@/views/user/operateaccount/addOperateaccount'
import updateOperateaccount
  from '@/views/user/operateaccount/updateOperateaccount'
import updatepassword from '@/views/user/updatepassword/updatepassword'
import weixinconfig from '@/views/weixin/weixinconfig/weixinconfig'
import docterlist from '@/views/docter/docterlist/docterlist'
import addDocter from '@/views/docter/docterlist/addDocter'
import addAl from '@/views/docter/docterlist/addAl'
import updateDocter from '@/views/docter/docterlist/updateDocter'
import doctercity from '@/views/docter/doctercity/doctercity'
import docterhot from '@/views/docter/docterhot/docterhot'
import imagetext from '@/views/imagetext/imagetext/imagetext'
import addImagetext from '@/views/imagetext/imagetext/addImagetext'
import updateImagetext from '@/views/imagetext/imagetext/updateImagetext'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      meta: {title: "矮芽-医生登录"}
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {title: "矮芽-医生登录"}
    },
    {
      path: '/main',
      name: 'Main',
      component: Main,
      meta: {title: "首页"},
      redirect: '/appointment',
      children: [
        {
          path: '/appointment',
          name: 'appointment',
          meta: {title: "预约中心"},
          component: appointment
        }, {
          path: '/userlist',
          name: 'userlist',
          meta: {title: "用户列表"},
          component: userlist
        }, {
          path: '/userbookcount',
          name: 'userbookcount',
          meta: {title: "预约次数"},
          component: userbookcount
        }, {
          path: '/uservisitcount',
          name: 'uservisitcount',
          meta: {title: "访问记录"},
          component: uservisitcount
        }, {
          path: '/searchhistory',
          name: 'searchhistory',
          meta: {title: "搜索历史"},
          component: searchhistory
        }, {
          path: '/operateaccount',
          name: 'operateaccount',
          meta: {title: "预约账号"},
          component: operateaccount
        }, {
          path: '/addOperateaccount',
          name: 'addOperateaccount',
          meta: {title: "新增运营账号"},
          component: addOperateaccount
        }, {
          path: '/updateOperateaccount',
          name: 'updateOperateaccount',
          meta: {title: "修改运营账号"},
          component: updateOperateaccount
        }, {
          path: '/updatepassword',
          name: 'updatepassword',
          meta: {title: "更改密码"},
          component: updatepassword
        }, {
          path: '/weixinconfig',
          name: 'weixinconfig',
          meta: {title: "微信管理"},
          component: weixinconfig
        }, {
          path: '/docterlist',
          name: 'docterlist',
          meta: {title: "医生列表"},
          component: docterlist
        }, {
          path: '/addDocter',
          name: 'addDocter',
          meta: {title: "添加医生"},
          component: addDocter
        }, {
          path: '/updateDocter',
          name: 'updateDocter',
          meta: {title: "修改医生"},
          component: updateDocter
        }, {
          path: '/addAl',
          name: 'addAl',
          meta: {title: "增加案例"},
          component: addAl
        }, {
          path: '/doctercity',
          name: 'doctercity',
          meta: {title: "城市管理"},
          component: doctercity
        }, {
          path: '/docterhot',
          name: 'docterhot',
          meta: {title: "热门医生"},
          component: docterhot
        }, {
          path: '/imagetext',
          name: 'imagetext',
          meta: {title: "图文管理"},
          component: imagetext
        }, {
          path: '/addImagetext',
          name: 'addImagetext',
          meta: {title: "新增图文"},
          component: addImagetext
        }, {
          path: '/updateImagetext',
          name: 'updateImagetext',
          meta: {title: "修改图文"},
          component: updateImagetext
        }
      ]
    }
  ]
})
