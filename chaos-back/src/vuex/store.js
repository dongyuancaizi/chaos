// 引入vue
import Vue from 'vue'
// 引入vuex
import Vuex from 'vuex'
import VueXAlong from 'vuex-along'
// 使用vuex
Vue.use(Vuex)

// 1、state：创建初始化状态
const state = {
  // 放置初始状态
  userinfo:{
    token:'',
    userid:'',
    username:''
  }
}

// 2、mutations：创建改变状态的方法
const mutations = {
  SetUserinfo(state,data){
    state.userinfo.token=data.token;
    state.userinfo.userid=data.unique;
    state.userinfo.username=data.username;
  },
  RefreshToken(state,token){
    state.userinfo.token=data.token;
  }
}

// 3、getters：提供外部获取state
const getters = {
  getUserinfo: function(state){
    return state.userinfo;
  }
}

// 4、actions：创建驱动方法改变mutations
const actions = {
  // 触发mutations中相应的方法-一般小写
  setUserinfo({commit}, data) {
    commit('SetUserinfo', data)
  },
  refreshToken({commit}, data) {
    commit('RefreshToken', data)
  }
}

// 5、全部注入Store中
const store = new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
  plugins: [VueXAlong()]
});

// 6、输出store
export default store;
