<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps
      :active="2"
      process-status="wait"
      align-center
      style="margin-bottom: 40px;"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="确认课程发布" />
    </el-steps>

    <!-- 后面优化的时候，可以将这个部分，使用树形结构来展示 -->
    <el-form label-width="120px">
      <el-button type="text" @click="openAddChapterDialog">添加章节</el-button>
      <!-- 添加和修改章节表单 -->
      <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
        <el-form :model="eduChapter" label-width="120px">
          <el-form-item label="章节标题">
            <el-input v-model="eduChapter.title" />
          </el-form-item>
          <el-form-item label="章节排序">
            <el-input-number
              v-model="eduChapter.sort"
              :min="0"
              controls-position="right"
            />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addOrUpdateChapterCancelled">取 消</el-button>
          <el-button
            type="primary"
            @click="addOrUpdateChapter"
          >确 定</el-button
          >
        </div>
      </el-dialog>

      <!-- 添加和修改小节视频表单 -->
      <el-dialog :visible.sync="dialogVideoFormVisible" title="添加小节视频">
        <el-form :model="eduVideo" label-width="120px">
          <el-form-item label="小节视频标题">
            <el-input v-model="eduVideo.title" />
          </el-form-item>

          <el-form-item label="小节视频排序">
            <el-input-number
              v-model="eduVideo.sort"
              :min="0"
              controls-position="right"
            />
          </el-form-item>

          <el-form-item label="是否免费">
            <el-radio-group v-model="eduVideo.isFree">
              <el-radio :label="1">免费</el-radio>
              <el-radio :label="0">收费</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="上传视频">
            <el-upload
              :on-success="handleVodUploadSuccess"
              :on-remove="handleVodRemove"
              :before-remove="beforeVodRemove"
              :on-exceed="handleUploadExceed"
              :file-list="fileList"
              :action="BASE_API + '/service_vod/vod_video/upload_video'"
              :limit="1"
              class="upload-demo"
            >
              <el-button size="small" type="primary">上传视频</el-button>
              <el-tooltip placement="right-end">
                <div slot="content">
                  最大支持1G，
                  <br >支持3GP、ASF、AVI、DAT、DV、FLV、F4V、
                  <br >GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、
                  <br >MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、
                  <br >SWF、TS、VOB、WMV、WEBM 等视频格式上传
                </div>
                <i class="el-icon-question" />
              </el-tooltip>
            </el-upload>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addOrUpdateVideoCancelled">取 消</el-button>
          <el-button
            :disabled="saveVideoBtnDisabled"
            type="primary"
            @click="addOrUpdateVideo"
          >确 定</el-button
          >
        </div>
      </el-dialog>

      <!-- 章节 -->
      <ul class="chanpterList">
        <li v-for="chapter in chapterList" :key="chapter.id">
          <p>
            {{ chapter.title }}
            <span class="acts">
              <!-- 添加小节 -->
              <el-button
                type="text"
                @click="openAddVideoDialog(chapter.id)"
              >添加小节</el-button
              >
              <!-- 编辑 -->
              <el-button
                style
                type="text"
                @click="openUpdateChapterDialog(chapter.id)"
              >编辑</el-button
              >
              <!-- 删除 -->
              <el-button
                type="text"
                @click="deleteChapterById(chapter.id)"
              >删除</el-button
              >
            </span>
          </p>

          <!-- 视频 -->
          <ul class="chanpterList videoList">
            <li v-for="video in chapter.child" :key="video.id">
              <p>
                {{ video.title }}
                <span class="acts">
                  <el-button
                    type="text"
                    @click="openUpdateVideoDialog(video.id)"
                  >编辑</el-button
                  >
                  <el-button
                    type="text"
                    @click="deleteVideoById(video.id)"
                  >删除</el-button
                  >
                </span>
              </p>
            </li>
          </ul>
        </li>
      </ul>
      <div>
        <el-button @click="previous">上一步</el-button>
        <el-button
          :disabled="nextStepBtnDisabled"
          type="primary"
          @click="next"
        >下一步</el-button
        >
      </div>
    </el-form>
  </div>
</template>

<script>
import edu_chapter_api from '@/api/edu_chapter'
import edu_video_api from '@/api/edu_video'

export default {
  data() {
    return {
      eduCourseId: '',
      nextStepBtnDisabled: false, // 下一步按钮是否禁用
      saveVideoBtnDisabled: false, // 保存章节按钮是否禁用
      chapterList: [], // 章节列表
      dialogChapterFormVisible: false, // 章节对话框是否展示
      dialogVideoFormVisible: false, // 小节视频对话框是否展示
      eduChapter: {}, // 章节表单
      eduVideo: {}, // 小节视频表单
      fileList: [], // 上传文件列表
      BASE_API: process.env.BASE_API // 接口API地址
    }
  },

  created() {
    console.log('chapter created')
    if (this.$route.params && this.$route.params.id) {
      this.eduCourseId = this.$route.params.id
      this.getChapterListByCourseId()
    } else {
      this.$message.console.error(
        '前端参数传递发生错误，请联系系统管理员解决问题！'
      )
    }
  },

  methods: {
    previous() {
      console.log('previous')
      this.$router.push({ path: `/edu_course/course_add/${this.eduCourseId}` })
    },
    next() {
      console.log('next')
      this.$router.push({
        path: `/edu_course/course_publish/${this.eduCourseId}`
      })
    },
    getChapterListByCourseId() {
      edu_chapter_api
        .getChapterListByCourseId(this.eduCourseId)
        .then(responese => {
          this.chapterList = responese.data.chapterList
        })
    },

    // 章节操作
    openAddChapterDialog() {
      this.dialogChapterFormVisible = true
      this.eduChapter = {}
    },
    openUpdateChapterDialog(chapterId) {
      edu_chapter_api.getChapterById(chapterId).then(response => {
        this.eduChapter = response.data.eduChapter
        this.dialogChapterFormVisible = true
      })
    },
    addOrUpdateChapter() {
      if (this.eduChapter.id) {
        this.updateChapterById()
      } else {
        this.addChapter()
      }
    },
    updateChapterById() {
      edu_chapter_api.updateChapterById(this.eduChapter).then(response => {
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
        this.dialogChapterFormVisible = false
        this.getChapterListByCourseId()
      })
    },
    addChapter() {
      this.eduChapter.courseId = this.eduCourseId
      edu_chapter_api.addChapter(this.eduChapter).then(() => {
        this.$message({
          type: 'success',
          message: '添加成功!'
        })
        this.dialogChapterFormVisible = false
        this.getChapterListByCourseId()
      })
    },
    addOrUpdateChapterCancelled() {
      this.dialogChapterFormVisible = false
    },
    deleteChapterById(id) {
      this.$confirm('此操作将永久删除该章节, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          edu_chapter_api.deleteChapterById(id).then(responese => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.getChapterListByCourseId()
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除!'
          })
        })
    },

    // 章节对应的 小节视频
    openAddVideoDialog(chapterId) {
      this.eduVideo = {}
      this.fileList = []
      this.dialogVideoFormVisible = true
      this.eduVideo.chapterId = chapterId
    },
    openUpdateVideoDialog(videoId) {
      this.eduVideo = {}
      this.fileList = []
      edu_video_api.getVideoById(videoId).then(response => {
        this.eduVideo = response.data.eduVideo
        if (this.eduVideo.videoOriginalName) {
          const file = {
            name: this.eduVideo.videoOriginalName,
            url: '/#'
          }
          this.fileList.push(file)
        }
        this.dialogVideoFormVisible = true
      })
    },
    addOrUpdateVideo() {
      if (this.eduVideo.id) {
        this.updateVideoById()
      } else {
        this.addVideo()
      }
    },
    updateVideoById() {
      console.log(this.eduVideo)
      edu_video_api.updateVideoById(this.eduVideo).then(response => {
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
        this.dialogVideoFormVisible = false
        this.getChapterListByCourseId()
      })
    },
    addVideo() {
      this.eduVideo.courseId = this.eduCourseId
      edu_video_api.addVideo(this.eduVideo).then(() => {
        this.$message({
          type: 'success',
          message: '添加成功!'
        })
        this.dialogVideoFormVisible = false
        this.getChapterListByCourseId()
      })
    },
    addOrUpdateVideoCancelled() {
      this.dialogVideoFormVisible = false
    },
    deleteVideoById(id) {
      this.$confirm('此操作将永久删除该小节视频, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          edu_video_api.deleteVideoById(id).then(responese => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.getChapterListByCourseId()
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除!'
          })
        })
    },
    // 成功回调
    handleVodUploadSuccess(response, file, fileList) {
      this.eduVideo.videoSourceId = response.data.videoId
      this.eduVideo.videoOriginalName = response.data.videoOriginalName
    },
    // 视图上传多于一个视频
    handleUploadExceed(files, fileList) {
      this.$message.warning('想要重新上传视频，请先删除已上传的视频')
    },
    handleVodRemove() {},
    beforeVodRemove() {}
  }
}
</script>

<style scoped>
.chanpterList {
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}
.chanpterList li {
  position: relative;
}
.chanpterList p {
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #ddd;
}
.chanpterList .acts {
  float: right;
  font-size: 14px;
}

.videoList {
  padding-left: 50px;
}
.videoList p {
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #ddd;
}
</style>
