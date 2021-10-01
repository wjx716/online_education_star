<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps
      :active="1"
      process-status="wait"
      align-center
      style="margin-bottom: 40px;"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="确认课程发布" />
    </el-steps>

    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input
          v-model="courseInfoForm.title"
          placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"
        />
      </el-form-item>

      <!-- 所属分类：级联下拉列表 -->
      <el-form-item label="课程类别">
        <!-- 父级分类 -->
        <el-select
          v-model="courseInfoForm.subjectParentId"
          placeholder="父级分类"
          @change="
            setChildSubjectListByParentId(courseInfoForm.subjectParentId)
          "
        >
          <el-option
            v-for="parentSubject in parentSubjectList"
            :key="parentSubject.id"
            :label="parentSubject.title"
            :value="parentSubject.id"
          />
        </el-select>
        <!-- 子级分类 -->
        <el-select v-model="courseInfoForm.subjectId" placeholder="子级分类">
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
        <el-select v-model="courseInfoForm.teacherId" placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="总课时">
        <el-input-number
          :min="0"
          v-model="courseInfoForm.lessonNum"
          controls-position="right"
          placeholder="请填写课程的总课时数"
        />
      </el-form-item>

      <!-- 课程简介-->
      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfoForm.description" />
      </el-form-item>

      <!-- 课程封面-->
      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :on-error="handleAvatarError"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API + '/service_oss/oss_file/upload_file'"
          class="avatar-uploader"
        >
          <img :src="courseInfoForm.cover" >
        </el-upload>
      </el-form-item>

      <el-form-item label="课程价格">
        <el-input-number
          :min="0"
          v-model="courseInfoForm.price"
          controls-position="right"
          placeholder="免费课程请设置为0元"
        />元
      </el-form-item>

      <el-form-item>
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="next"
        >保存并下一步</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import edu_course_api from '@/api/edu_course'
import edu_teacher_api from '@/api/edu_teacher'
import edu_subject_api from '@/api/edu_subject'
import Tinymce from '@/components/Tinymce'

export default {
  components: { Tinymce },
  data() {
    return {
      eduCourseId: '', // 返回的课程ID
      saveBtnDisabled: false, // 保存按钮是否禁用
      courseInfoForm: {
        subjectId: '',
        cover: '/default_cover.jpg'
      }, // 课程基本信息
      teacherList: [], // 讲师下拉框数据
      parentSubjectList: [], // 父科目，包含了子科目的数据
      childSubjectList: [], // 父科目，包含了子科目的数据
      BASE_API: process.env.BASE_API // 接口API地址
    }
  },
  created() {
    if (this.$route.params && this.$route.params.id) {
      this.eduCourseId = this.$route.params.id
      this.getCourseInfoById()
    } else {
      this.getAllSubjectTree()
    }
    this.getTeacherList()
  },
  methods: {
    next() {
      if (this.courseInfoForm.id) {
        this.updateCourseInfo()
      } else {
        this.addCourseInfo()
      }
    },
    addCourseInfo() {
      edu_course_api.addCourseInfo(this.courseInfoForm).then(response => {
        this.$message({
          type: 'success',
          message: '添加成功!'
        })
        this.eduCourseId = response.data.eduCourseId
        this.$router.push({ path: `/edu_course/course_chapter/${this.eduCourseId}` })
      })
    },
    updateCourseInfo() {
      edu_course_api.updateCourseInfo(this.courseInfoForm).then(response => {
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
        this.$router.push({ path: `/edu_course/course_chapter/${this.eduCourseId}` })
      })
    },
    getCourseInfoById() {
      edu_course_api.getCourseInfoById(this.eduCourseId).then(response => {
        // 数据回显
        this.courseInfoForm = response.data.courseInfo
        edu_subject_api
          .getAllSubjectTree()
          .then(response => {
            // 初始化父科目的下拉框
            this.parentSubjectList = response.data.allSubjectTree
          })
          .then(() => {
            // 初始化子科目的下拉框
            this.parentSubjectList.forEach(parentSubject => {
              if (parentSubject.id === this.courseInfoForm.subjectParentId) {
                this.childSubjectList = parentSubject.child
              }
            })
          })
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
      this.courseInfoForm.subjectId = ''
      this.parentSubjectList.forEach(parentSubject => {
        if (parentSubject.id === parentSubjectId) {
          this.childSubjectList = parentSubject.child
        }
      })
    },
    // 上传成功后方法
    handleAvatarSuccess(res, file) {
      this.courseInfoForm.cover = res.data.url
    },
    // 校验课程封面
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    // 部分jpg文件不能上传到服务器时（部分极少数jpg文件上传，会触发跨域），提示更换图片
    handleAvatarError() {
      this.$message.error('封面修改失败，该图片文件不支持上传，请更换图片')
    }
  }
}
</script>

<style scoped>
.tinymce-container {
  line-height: 28px;
}
</style>
