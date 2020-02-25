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
  count: 1,
  me: {
    name: 'cui',
    age: 29,
    sex: '男'
  },
  list: [
    {name: 'tea', age: 2,}, {name: 'niu', age: 4}
  ],
  userinfo:{
    token:'',
    userid:'',
    username:''
  }
}

// 2、mutations：创建改变状态的方法
const mutations = {
  // 状态变更函数-一般大写
  ADD(state, n) {
    state.count += n;
  },
  Change() {
    state.me.age = 30
  },
  SetUserinfo(state,data){
    state.userinfo.token=data.token;
    state.userinfo.userid=data.unique;
    state.userinfo.username=data.username;
  }
}

// 3、getters：提供外部获取state
const getters = {
  count: function (state) {
    return state.count;
  },
  getUserinfo: function(state){
    return state.userinfo;
  }
}

// 4、actions：创建驱动方法改变mutations
const actions = {
  // 触发mutations中相应的方法-一般小写
  add({commit}, data) {
    commit('ADD', data)
  },
  setUserinfo({commit}, data) {
    commit('SetUserinfo', data)
  }
}

// 5、全部注入Store中
const store = new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
  plugins: [VueXAlong({
    name: 'along',     //存放在localStroage或者sessionStroage 中的名字
    local: false,      //是否存放在local中  false 不存放 如果存放按照下面session的配置配
    session: { list: [], isFilter: true } //如果值不为false 那么可以传递对象 其中 当isFilter设置为true时， list 数组中的值就会被过滤调,这些值不会存放在seesion或者local中
  })]
});

// 6、输出store
export default store;
