<template>
  <div class="app-container">
    <h2>讲师列表</h2>
    <!-- 条件查询表单 -->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="讲师名">
        <el-input v-model="teacherQuery.name" placeholder="讲师名" />
      </el-form-item>

      <el-form-item label="讲师头衔">
        <el-select
          v-model="teacherQuery.level"
          clearable
          placeholder="讲师头衔"
        >
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="teacherQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="teacherQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button
        type="primary"
        icon="el-icon-search"
        @click="getTeacherPageByQuery()"
      >查询</el-button
      >
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <!-- v-loading="listLoading" -->
    <el-table
      v-loading="listLoading"
      :data="teacherList"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (current - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="name" label="名称" width="80" />

      <el-table-column label="头衔" width="80">
        <template slot-scope="scope">
          {{ scope.row.level === 1 ? '高级讲师' : '首席讲师' }}
        </template>
      </el-table-column>

      <el-table-column prop="intro" label="资历" />

      <el-table-column prop="gmtCreate" label="添加时间" width="160" />

      <el-table-column prop="sort" label="排序" width="60" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <!-- 修改 -->
          <router-link :to="'/edu_teacher/teacher_update/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">
              修改
            </el-button>
          </router-link>

          <!-- 删除 -->
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="deleteTeacherById(scope.row.id)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      background
      :current-page.sync="current"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next"
      @current-change="getTeacherPageByQuery"
    />
  </div>
</template>

<script>
import edu_teacher_api from '@/api/edu_teacher'
import constant from '@/constant/constant'

export default {
  data() {
    return {
      current: 1,
      limit: 5,
      total: 0,
      teacherQuery: {},
      teacherList: [],
      listLoading: false,
    }
  },
  created() {
    this.getTeacherPageByQuery();
  },
  methods: {
    // 重置查询条件表单
    resetData() {
      this.teacherQuery = {}
      this.getTeacherPageByQuery()
    },
    // 获取讲师列表
    getTeacherPageByQuery(current = 1) {
      this.current = current
      this.listLoading = true
      edu_teacher_api
        .getTeacherPageByQuery(this.current, this.limit, this.teacherQuery)
        .then(returnResult => {
          this.teacherList = returnResult.data.list
          this.total = returnResult.data.total
          this.listLoading = false
        })
    },
    // 根据ID，删除讲师
    deleteTeacherById(id) {
      this.$confirm('此操作将永久删除该讲师, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          edu_teacher_api.deleteTeacherById(id).then(returnResult => {
            if (returnResult.code === constant.CODE_SUCCESS) {
              this.$message({
                type: 'success',
                message: '删除成功'
              })
              if (this.teacherList.length === 1) {
                this.current--;
              }
            } else {
              this.$message.error('删除失败')
            }
            this.getTeacherPageByQuery(this.current)
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    //
  }
}
</script>
