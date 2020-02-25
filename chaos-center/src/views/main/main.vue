<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>代码中台</h2>
    <ul>
      <li><a href="http://center.okyakid.com:8080/code-generator/"
             target="_blank">代码生成</a></li>
      <li><a href="http://git.okyakid.com/" target="_blank">代码仓库</a></li>
      <li><a href="http://center.okyakid.com:8081/#admin/security/users"
             target="_blank">构件仓库</a></li>
      <li><a href="http://center.okyakid.com:8288/harbor/projects"
             target="_blank">镜像仓库</a></li>
      <br>
      <li><a href="https://center.okyakid.com:9000/" target="_blank">环境管理</a>
      </li>
      <li><a href="http://center.okyakid.com:8848/nacos/#"
             target="_blank">配置中心</a>
      </li>
      <br>
    </ul>
    <h2>监控中台</h2>
    <h3>Boss环境</h3>
    <ul>
      <li><a href="http://center.okyakid.com:15672/#/exchanges"
             target="_blank">消息中台</a></li>
      <li><a href="http://center.okyakid.com:18888/#/" target="_blank">服务治理</a>
      </li>
      <li><a href="http://center.okyakid.com:9411/zipkin/"
             target="_blank">链路跟踪</a>
      </li>
      <li><a href="http://center.okyakid.com:8000/" target="_blank">微服务管理</a>
      </li>
      <br>
      <li><a href="http://center.okyakid.com:28083/swagger-ui.html#"
             target="_blank">API管理</a>
      </li>
      <li><a href="http://center.okyakid.com:28083/druid/datasource.html"
             target="_blank">数据库监控</a></li>
      <li><a href="http://center.okyakid.com:19999/xxl-job-admin/jobgroup"
             target="_blank">任务中台</a></li>
    </ul>
    <h3>Tx环境</h3>
    <ul>
      <li><a href="http://tx.okyakid.com:15672/#/exchanges"
             target="_blank">消息中台</a></li>
      <li><a href="http://tx.okyakid.com:18888/#/" target="_blank">服务治理</a>
      </li>
      <li><a href="http://tx.okyakid.com:9411/zipkin/"
             target="_blank">链路跟踪</a>
      </li>
      <li><a href="http://tx.okyakid.com:8000/" target="_blank">微服务管理</a>
      </li>
      <li><a href="http://tx.okyakid.com:19999/xxl-job-admin/jobgroup"
             target="_blank">任务中台</a></li>
      <br>
      <li><a href="http://tx.okyakid.com:28083/swagger-ui.html#"
             target="_blank">API管理</a>
      </li>
      <li><a href="http://tx.okyakid.com:28083/druid/datasource.html"
             target="_blank">数据库监控</a></li>
      <li><a href="" target="_blank">管理后台</a></li>
    </ul>
    <h3>Dev环境</h3>
    <ul>
      <li><a href="http://dev.okyakid.com:15672/#/exchanges"
             target="_blank">消息中台</a></li>
      <li><a href="http://dev.okyakid.com:18888/#/" target="_blank">服务治理</a>
      </li>
      <li><a href="http://dev.okyakid.com:9411/zipkin/"
             target="_blank">链路跟踪</a>
      </li>
      <li><a href="http://dev.okyakid.com:8000/" target="_blank">微服务管理</a>
      </li>
      <li><a href="http://dev.okyakid.com:19999/xxl-job-admin/jobgroup"
             target="_blank">任务中台</a></li>
      <br>
      <li><a href="http://dev.okyakid.com:28083/swagger-ui.html#"
             target="_blank">API管理</a>
      </li>
      <li><a href="http://dev.okyakid.com:28083/druid/datasource.html"
             target="_blank">数据库监控</a></li>
      <li><a href="" target="_blank">管理后台</a></li>
    </ul>
  </div>
</template>


<script>
    import fetch from '../../axios/fetch'
    import store from '../../vuex/store'
    export default {
        store,
        name: 'HelloWorld',
        data() {
            return {
                msg: '欢 迎 进 入 中 台 系 统'
            }
        },
        mounted() {
            if (!this.$store.getters.getUserinfo.username){
                this.$router.push('/')
            }
            fetch.get('/web/getMessage').then((res) => {
                console.log(res)
            }, (reason) => {
                console.log(reason)
            })
        }
    }
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->


<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>




