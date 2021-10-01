<template>
  <div class="app-container">
    <!-- 条件查询表单 -->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="课程标题">
        <el-input v-model="courseQuery.title" placeholder="课程标题" />
      </el-form-item>

      <!-- 所属分类：级联下拉列表 -->
      <el-form-item label="课程类别">
        <!-- 父级分类 -->
        <el-select
          v-model="courseQuery.subjectParentId"
          placeholder="父级分类"
          @change="setChildSubjectListByParentId(courseQuery.subjectParentId)"
        >
          <el-option
            v-for="parentSubject in parentSubjectList"
            :key="parentSubject.id"
            :label="parentSubject.title"
            :value="parentSubject.id"
          />
        </el-select>
        <!-- 子级分类 -->
        <el-select v-model="courseQuery.subjectId" placeholder="子级分类">
          <el-option
            v-for="childSubject in childSubjectList"
            :key="childSubject.id"
            :label="childSubject.title"
            :value="childSubject.id"
          />
        </el-select>
      </el-form-item>

      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseQuery.teacherId" placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"
          />
        </el-select>
      </el-form-item>

      <el-button
        type="primary"
        icon="el-icon-search"
        @click="getCoursePageByQuery()"
      >查询</el-button
      >
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table
      v-loading="listLoading"
      :data="courseList"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row
      row-class-name="myClassList"
    >
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">{{
          (current - 1) * limit + scope.$index + 1
        }}</template>
      </el-table-column>

      <el-table-column label="课程信息" width="310" align="center">
        <template slot-scope="scope">
          <div class="info">
            <div class="pic">
              <img :src="scope.row.cover" alt="scope.row.title" width="150px" >
            </div>
            <div class="title">
              <router-link :to="'/edu_course/course_add/' + scope.row.id">{{
                scope.row.title
              }}</router-link>
            </div>
          </div>
        </template>
      </el-table-column>

      <el-table-column label="课时" width="105" align="center">
        <template slot-scope="scope">
          <p>{{ scope.row.lessonNum }} 小时</p>
        </template>
      </el-table-column>

      <el-table-column label="创建时间" align="center" width="165">
        <template slot-scope="scope">{{
          scope.row.gmtCreate.substr(0, 10)
        }}</template>
      </el-table-column>
      <el-table-column label="发布时间" align="center" width="165">
        <template slot-scope="scope">{{
          scope.row.gmtModified.substr(0, 10)
        }}</template>
      </el-table-column>
      <el-table-column label="价格" width="165" align="center">
        <template slot-scope="scope">
          {{
            Number(scope.row.price) === 0
              ? '免费'
              : '¥' + scope.row.price.toFixed(2)
          }}
        </template>
      </el-table-column>
      <el-table-column
        prop="buyCount"
        label="付费学员"
        width="165"
        align="center"
      >
        <template
          slot-scope="scope"
        >{{ scope.row.buyCount }}人</template
        >
      </el-table-column>

      <el-table-column label="操作" width="170" align="center">
        <template slot-scope="scope">
          <router-link :to="'/edu_course/course_add/' + scope.row.id">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
            >编辑课程信息</el-button
            >
          </router-link>
          <br ><br >
          <router-link :to="'/edu_course/course_chapter/' + scope.row.id">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
            >编辑课程大纲</el-button
            >
          </router-link>
          <br ><br >
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="deleteCourseRelatedById(scope.row.id)"
          >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page.sync="current"
      :page-size="limit"
      :total="total"
      background
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next"
      @current-change="getCoursePageByQuery"
    />
  </div>
</template>

<script>
import edu_course_api from '@/api/edu_course'
import edu_teacher_api from '@/api/edu_teacher'
import edu_subject_api from '@/api/edu_subject'

export default {
  data() {
    return {
      listLoading: true, // 是否显示loading信息
      courseList: [], // 课程数据列表
      courseQuery: {}, // 课程查询条件
      current: 1, // 当前页码
      limit: 3, // 每页记录数
      total: 0, // 总记录数
      teacherList: [], // 讲师下拉框数据
      parentSubjectList: [], // 父科目，包含了子科目的数据
      childSubjectList: [] // 父科目，包含了子科目的数据
    }
  },
  created() {
    this.getCoursePageByQuery()
    this.getTeacherList()
    this.getAllSubjectTree()
  },
  methods: {
    getCoursePageByQuery(current = 1) {
      this.current = current
      this.listLoading = true
      edu_course_api
        .getCoursePageByQuery(this.current, this.limit, this.courseQuery)
        .then(response => {
          this.courseList = response.data.list
          this.total = response.data.total
          this.listLoading = false
        })
    },
    getCourseList() {
      edu_course_api.getCourseList().then(response => {
        this.courseList = response.data.courseList
        this.listLoading = false
      })
    },
    getTeacherList() {
      edu_teacher_api.getAllTeacher().then(response => {
        this.teacherList = response.data.list
      })
    },
    getAllSubjectTree() {
      edu_subject_api.getAllSubjectTree().then(response => {
        this.parentSubjectList = response.data.allSubjectTree
      })
    },
    setChildSubjectListByParentId(parentSubjectId) {
      this.courseQuery.subjectId = ''
      this.parentSubjectList.forEach(parentSubject => {
        if (parentSubject.id === parentSubjectId) {
          this.childSubjectList = parentSubject.child
        }
      })
    },
    // 重置查询条件表单
    resetData() {
      this.courseQuery = {}
      this.getCoursePageByQuery()
    },
    // 根据ID，删除课程相关的数据
    deleteCourseRelatedById(id) {
      edu_course_api.deleteCourseRelatedById(id).then(response => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
        this.getCoursePageByQuery()
      })
      
    }
  }
}
</script>
