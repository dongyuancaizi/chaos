<template>
  <el-container>
    <el-menu
      default-active="/appointment"
      class="side-menu"
      @open="handleOpen"
      @close="handleClose"
      @select="handleSelect"
      router
      background-color="#fff"
      text-color="#545c64"
      active-text-color="#438eb9">
      <el-submenu v-for="(menu,i) in menus" :key="i" :index="i+''">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>{{menu.title}}</span>
        </template>
        <el-menu-item v-for="(item,i) in menu.submenus" :key="i"
                      :index="item.link">
          <template slot="title">
            <span>{{item.title}}</span>
          </template>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </el-container>
</template>
<script>
    import store from '@/vuex/store';

    export default {
        store,
        data() {
            return {
                menus: []
            }
        },
        mounted() {
            if (store.state.userinfo.username == 'we91iya@126.com') {
                this.menus = [{
                    title: '用户中心',
                    submenus: [
                        {title: '预约中心', link: '/appointment'},
                        {title: '用户列表', link: '/userlist'},
                        {title: '搜索历史', link: '/searchhistory'},
                        {title: '运营账号列表', link: '/operateaccount'},
                        {title: '修改密码', link: '/updatepassword'}
                    ]
                }, {
                    title: '微信管理',
                    submenus: [
                        {title: '配置', link: '/weixinconfig'}
                    ]
                }, {
                    title: '医生管理',
                    submenus: [
                        {title: '医生列表', link: '/docterlist'},
                        {title: '医生城市', link: '/doctercity'},
                        {title: '热门医生', link: '/docterhot'}
                    ]
                }, {
                    title: '图文管理',
                    submenus: [
                        {title: '图文管理', link: '/imagetext'}
                    ]
                }]
            } else {
                this.menus = [{
                    title: '医生管理',
                    submenus: [
                        {title: '医生列表', link: '/docterlist'}
                    ]
                }]
            }
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>
<style scoped>

</style>
