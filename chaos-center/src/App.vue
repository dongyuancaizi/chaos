<template>
  <div id="app">
    <p style="float:right">用户 : {{$store.state.userinfo.username}}</p>
    <img src="./assets/pongo128.png">
    <router-view v-wechat-title="$route.meta.title"/>
  </div>
</template>

<script>
    import store from '@/vuex/store'

    export default {
        store,
        name: 'App'
    }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
