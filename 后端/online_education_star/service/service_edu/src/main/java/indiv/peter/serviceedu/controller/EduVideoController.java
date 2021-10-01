package indiv.peter.serviceedu.controller;


import com.baomidou.mybatisplus.extension.api.R;
import indiv.peter.commonutils.Result;
import indiv.peter.serviceedu.client.VodClient;
import indiv.peter.serviceedu.entity.EduVideo;
import indiv.peter.serviceedu.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-22
 */
@Api(tags = {"小节视频管理"})
@CrossOrigin
@RestController
@RequestMapping("/service_edu/edu_video")
public class EduVideoController {

    private final EduVideoService eduVideoService;
    private final VodClient vodClient;

    @Autowired
    public EduVideoController(EduVideoService eduVideoService, VodClient vodClient) {
        this.eduVideoService = eduVideoService;
        this.vodClient = vodClient;
    }

    @ApiOperation(value = "添加小节视频")
    @PostMapping("add_video")
    public Result addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return Result.ok();
    }

    /**
     * TODO 删除小节的同时，删除阿里云视频
     *
     * @param id 小节视频id
     * @return 操作结果
     */
    @ApiOperation(value = "根据小节视频id，删除小节视频")
    @DeleteMapping("delete_video_by_id/{id}")
    public Result deleteVideoById(@PathVariable String id) {
        EduVideo eduVideo = eduVideoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if (videoSourceId != null) {
            vodClient.deleteVideo(videoSourceId);
        }
        eduVideoService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据小节视频id，查询小节视频")
    @GetMapping("get_video_by_id/{id}")
    public Result getVideoById(@PathVariable String id) {
        EduVideo eduVideo = eduVideoService.getById(id);
        return Result.ok().data("eduVideo", eduVideo);
    }

    @ApiOperation(value = "根据小节视频id，修改小节视频")
    @PostMapping("update_video_by_id")
    public Result updateVideoById(@RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);
        return Result.ok();
    }
}

