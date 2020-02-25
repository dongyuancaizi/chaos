<template>
  <el-container
    style="margin: 0px auto;display:flex;width:100%;background-color:#438eb9;height: 60px ">
    <el-container style="flex: 6;">
      <img style="height: 25px;width: 25px;margin:auto 0px auto 20px;"
           src="@/assets/images/logo-head.png"/>
      <span style="font-size:20px;color: white;margin:auto 0px ">矮芽管理系统</span>
    </el-container>
    <el-container style="flex: 1;margin: auto 0px ;">
      <el-dropdown>
       <span class="el-dropdown-link">
           <div
             v-if="$store.state.userinfo.username=='we91iya@126.com'">矮芽管理员</div>
           <div v-else>运营人员</div>
                {{$store.state.userinfo.username}}
          <i class="el-icon-caret-bottom"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-container>
  </el-container>


</template>

<script>
    import store from '@/vuex/store';

    export default {
        store,
        methods: {
            logout() {
                clearVuexAlong()
                setTimeout(function () {
                    this.$router.push('/');
                }.bind(this), 500)
            }
        }
    }
</script>
<style scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: white;
    font-size: 16px;
  }

  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
