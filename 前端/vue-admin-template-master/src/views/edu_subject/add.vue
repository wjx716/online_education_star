<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="信息描述">
        <el-tag type="info">excel模版说明</el-tag>
        <el-tag>
          <i class="el-icon-download" />
          <a :href="OSS_API + '/excel/%E8%AF%BE%E7%A8%8B%E7%A7%91%E7%9B%AE%E6%95%B0%E6%8D%AE.xlsx'">
            点击下载模版
          </a>
        </el-tag>
      </el-form-item>

      <el-form-item label="选择Excel">
        <el-upload
          ref="upload"
          :auto-upload="false"
          :on-success="fileUploadSuccess"
          :on-error="fileUploadError"
          :disabled="importBtnDisabled"
          :limit="1"
          :action="BASE_API + '/service_edu/edu_subject/import_subject_from_excel'"
          name="file"
          accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        >
          <el-button
            slot="trigger"
            size="small"
            type="primary"
          >选取文件</el-button
          >
          <el-button
            :loading="loading"
            style="margin-left: 10px;"
            size="small"
            type="success"
            @click="submitUpload"
          >上传提交</el-button
          >
        </el-upload>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      BASE_API: process.env.BASE_API, // 服务接口API地址
      OSS_API: process.env.OSS_API, // OSS接口API地址
      importBtnDisabled: false, // 按钮是否禁用,
      loading: false
    }
  },
  created() {},
  methods: {
    submitUpload() {
      this.importBtnDisabled = true
      this.loading = true
      this.$refs.upload.submit()
    },
    fileUploadSuccess(response) {
      this.$message({
        type: 'success',
        message: '导入成功!'
      })
      this.importBtnDisabled = false
      this.loading = false
      // 跳转课程分类列表 TODO
    },
    fileUploadError(response) {
      this.$message.error('导入失败！')
      this.importBtnDisabled = false
      this.loading = false
    }
  }
}
</script>
