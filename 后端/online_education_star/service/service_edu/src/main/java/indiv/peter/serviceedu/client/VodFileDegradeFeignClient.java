package indiv.peter.serviceedu.client;

import indiv.peter.commonutils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author PeterRays
 * @date 2021/9/26
 */
@Component
public class VodFileDegradeFeignClient implements  VodClient{
    @Override
    public Result deleteVideo(String videoId) {
        return Result.error().message("删除失败");
    }

    @Override
    public Result deleteVideoList(List<String> videoIdList) {
        return Result.error().message("批量删除失败");
    }

    @Override
    public Result getVideoPlayAuth(String vid) {
        return Result.error().message("获取视频失败，请联系系统管理员解决问题！");
    }
}
