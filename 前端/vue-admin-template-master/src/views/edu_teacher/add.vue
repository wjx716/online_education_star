<template>
  <div class="app-container">
    <h2>添加讲师</h2>
    <!-- 讲师表单 -->
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="eduTeacher.name" />
      </el-form-item>

      <el-form-item label="讲师排序">
        <el-input-number
          v-model="eduTeacher.sort"
          :min="0"
          controls-position="right"
        />
      </el-form-item>

      <el-form-item label="讲师头衔">
        <el-select v-model="eduTeacher.level" clearable placeholder="请选择">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
          -->
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>

      <el-form-item label="讲师资历">
        <el-input v-model="eduTeacher.career" />
      </el-form-item>

      <el-form-item label="讲师简介">
        <el-input v-model="eduTeacher.intro" :rows="10" type="textarea" />
      </el-form-item>

      <!-- 讲师头像 -->
      <el-form-item label="讲师头像">
        <!-- 头衔缩略图 -->
        <pan-thumb :image="eduTeacher.avatar" />
        <!-- 文件上传按钮 -->
        <el-button
          type="primary"
          icon="el-icon-upload"
          @click="imagecropperShow = true"
        >更换头像</el-button
        >
        <!--
          v-show：是否显示上传组件
          :key：类似于id，如果一个页面多个图片上传控件，可以做区分
          :url：后台上传的url地址
          @close：关闭上传组件
          @crop-upload-success：上传成功后的回调
        -->
        <image-cropper
          v-show="imagecropperShow"
          :width="300"
          :height="300"
          :key="imagecropperKey"
          :url="BASE_API + '/service_oss/oss_file/upload_file'"
          field="file"
          @close="close"
          @crop-upload-success="cropSuccess"
        />
      </el-form-item>

      <!-- 操作按钮 -->
      <el-form-item>
        <!-- 保存 -->
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="addOrUpdate"
        >保存</el-button
        >
        <!-- 返回 -->
        <el-button @click="$router.back()">
          返回
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import edu_teacher_api from '@/api/edu_teacher'
import constant from '@/constant/constant'
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'

export default {
  components: { ImageCropper, PanThumb },
  data() {
    return {
      eduTeacher: {}, // 表单数据，用于添加 或 修改
      saveBtnDisabled: false, // 按钮是否不可操作
      BASE_API: process.env.BASE_API, // 接口API地址
      imagecropperShow: false, // 是否显示上传组件
      imagecropperKey: 0 // 上传组件id
    }
  },
  watch: {
    $route(to, from) {
      this.init()
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      // 如果是修改按钮转跳过来的，就进入if，查询此ID对应的讲师数据
      if (this.$route.params && this.$route.params.id) {
        console.log("it's update")
        console.log('$route.params.id=' + this.$route.params.id)
        edu_teacher_api.getTeacherById(this.$route.params.id).then(response => {
          this.eduTeacher = response.data.eduTeacher
        })
      } else {
        console.log("it's add")
        this.eduTeacher = {}
      }
    },
    // 判断 是添加，还是更新
    addOrUpdate() {
      if (this.eduTeacher.id) {
        this.updateExistedTeacher()
      } else {
        this.addNewTeacher()
      }
    },
    // 更新讲师
    updateExistedTeacher() {
      edu_teacher_api.updateTeacherById(this.eduTeacher).then(response => {
        if (response.code === constant.CODE_SUCCESS) {
          // 提示成功
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
        } else {
          // 提示失败
          this.$message.error('修改失败，请重试，或联系系统管理员修复！')
        }

        // 路由跳转
        this.$router.push({ path: '/edu_teacher/teacher_list' })
      })
    },
    // 添加讲师
    addNewTeacher() {
      edu_teacher_api.addTeacher(this.eduTeacher).then(response => {
        if (response.code === constant.CODE_SUCCESS) {
          // 提示成功
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
        } else {
          // 提示失败
          this.$message.error('添加失败，请重试，或联系系统管理员修复！')
        }

        // 路由跳转
        this.$router.push({ path: '/edu_teacher/teacher_list' })
      })
    },
    // 关闭上传头像窗口
    close() {
      this.imagecropperShow = false
      // 上传失败后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
      this.imagecropperKey = this.imagecropperKey + 1
    },
    cropSuccess(data) {
      // 拿url，进行赋值
      this.eduTeacher.avatar = data.url
      // 关闭窗口
      this.close()
    }
  }
}
</script>
