import request from "@/utils/request";

export default {
  // 根据视频id，获取视频播放凭证
  getVideoPlayAuth(vid) {
    return request({
      url: `/service_edu/api_edu_vod/get_video_play_auth/${vid}`,
      method: "get"
    });
  }
};
