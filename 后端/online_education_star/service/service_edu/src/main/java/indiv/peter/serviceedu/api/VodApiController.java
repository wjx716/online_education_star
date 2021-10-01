package indiv.peter.serviceedu.api;

import indiv.peter.commonutils.Result;
import indiv.peter.serviceedu.client.VodClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author PeterRays
 * @date 2021/9/28
 */
@Api(tags= {"前台视频点播"})
@RestController
@RequestMapping("/service_edu/api_edu_vod")
@CrossOrigin
public class VodApiController {
    private final VodClient vodClient;

    @Autowired
    public VodApiController(VodClient vodClient) {
        this.vodClient = vodClient;
    }

    @ApiOperation(value = "根据视频id，获取视频播放凭证")
    @GetMapping("get_video_play_auth/{vid}")
    public Result getVideoPlayAuth(@PathVariable String vid){
        return vodClient.getVideoPlayAuth(vid);
    }
}
