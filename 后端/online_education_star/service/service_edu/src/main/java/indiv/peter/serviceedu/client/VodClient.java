package indiv.peter.serviceedu.client;

import com.baomidou.mybatisplus.extension.api.R;
import indiv.peter.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author PeterRays
 * @date 2021/9/25
 */
@Component
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
public interface VodClient {
    /**
     * 根据视频的SourceID，删除视频
     * @param videoId 视频的SourceID
     * @return 操作结果
     */
    @DeleteMapping("/service_vod/vod_video/delete_video/{videoId}")
    Result deleteVideo(@PathVariable("videoId") String videoId);

    /**
     * 根据List存储的视频的SourceID，批量删除视频
     * @param videoIdList List存储的视频的SourceID
     * @return 操作结果
     */
    @DeleteMapping("/service_vod/vod_video/delete_video_list")
    Result deleteVideoList(@RequestParam("videoIdList") List<String> videoIdList);

    /**
     * 根据视频id，获取视频播放凭证
     * @param vid 视频ID
     * @return 返回视频凭证
     */
    @GetMapping("/service_vod/vod_video/get_video_play_auth/{vid}")
    Result getVideoPlayAuth(@PathVariable String vid);
}
