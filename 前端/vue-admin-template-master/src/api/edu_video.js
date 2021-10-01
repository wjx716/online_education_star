import request from '@/utils/request'

export default {
  // 添加小节视频
  addVideo(eduVideo) {
    return request({
      url: `/service_edu/edu_video/add_video`,
      method: 'post',
      data: eduVideo
    })
  },
  // 根据小节视频id，删除小节视频
  deleteVideoById(id) {
    return request({
      url: `/service_edu/edu_video/delete_video_by_id/${id}`,
      method: 'delete'
    })
  },
  // 根据小节视频id，查询小节视频
  getVideoById(id) {
    return request({
      url: `/service_edu/edu_video/get_video_by_id/${id}`,
      method: 'get'
    })
  },
  // 根据小节视频id，修改小节视频
  updateVideoById(eduVideo) {
    return request({
      url: `/service_edu/edu_video/update_video_by_id`,
      method: 'post',
      data: eduVideo
    })
  }
}
