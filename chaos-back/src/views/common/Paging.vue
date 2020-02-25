<template>
  <div class="pagination">
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      :current-page.sync="currentPage"
      :page-size="limit"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
    export default {
        name: "pagination",
        data() {
            return {
                currentPage: 1
            }
        },
        props: {
            // 避免直接改变prop属性
            // 'currentPage': {
            //   required: false,
            //   default: 1
            // },
            'total': {
                required: false,
                default: 0
            },
            'limit': {
                required: false,
                default: 10
            },
            'small': {
                required: false,
                type: Boolean,
                default: false
            }
        },
        watch: {
            currentPage(val) {
                // 改变这个值并不会触发 handleCurrentChange
                if (typeof val === "number") {
                    console.log('prop currentPage!!!');
                    this.currentPage = val;
                }
            },
        },
        methods: {
            // 当前页变化
            handleCurrentChange(val) {
                this.$emit("handleCurrentChange", val);
            },
            // size变化
            handleSizeChange(val) {
                this.currentPage = 1;
                this.$emit('handleSizeChange', val);
            },
        }
    }
</script>

<style scoped>
  .pagination {
    margin: auto 0px;
    text-align: right;
  }
</style>
