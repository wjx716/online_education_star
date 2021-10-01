package indiv.peter.servicevod.controller;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import indiv.peter.commonutils.Result;
import indiv.peter.servicebase.exception.GlobalException;
import indiv.peter.servicevod.utils.AliyunVodSDKUtil;
import indiv.peter.servicevod.utils.ConstantPropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * @author PeterRays
 * @date 2021/9/24
 */
@Api(tags = {"视频管理"})
@RestController
@RequestMapping("/service_vod/vod_video")
@CrossOrigin
public class VideoAminController {

    @ApiOperation(value = "上传视频")
    @PostMapping("upload_video")
    public Result uploadVideo(MultipartFile file) {

        try {
            String originalFilename = file.getOriginalFilename();
            String title;
            if (originalFilename != null) {
                title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            } else {
                title = "EmptyFileName_" + UUID.randomUUID();
            }
            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                    title,
                    originalFilename,
                    inputStream
            );

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */

            String videoId = response.getVideoId();
            return Result.ok().data("videoId", videoId).data("videoOriginalName", originalFilename);

        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException("上传视频失败");
        }
    }

    @ApiOperation(value = "根据视频的SourceID，删除视频")
    @DeleteMapping("delete_video/{videoId}")
    public Result deleteVideo(@PathVariable("videoId") String videoId) {

        try {
            //*初始化客户端对象
            DefaultAcsClient client = AliyunVodSDKUtil.initVodClient(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET
            );
            //*创建请求对象（不同操作，类不同）
            DeleteVideoRequest request = new DeleteVideoRequest();
            //*创建响应对象（不同操作，类不同）
            //DeleteVideoResponse response = new DeleteVideoResponse();
            //*向请求中设置参数
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(videoId);
            //*调用客户端对象方法发送请求，拿到响应
            client.getAcsResponse(request);
            return Result.ok();

        } catch (ClientException e) {
            e.printStackTrace();
            throw new GlobalException("删除视频失败");
        }

    }

    @ApiOperation(value = "根据List存储的视频的SourceID，批量删除视频")
    @DeleteMapping("delete_video_list")
    public Result deleteVideoList(@RequestParam("videoIdList") List<String> videoIdList) {
        try {
            //*初始化客户端对象
            DefaultAcsClient client = AliyunVodSDKUtil.initVodClient(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET
            );
            //*创建请求对象（不同操作，类不同）
            DeleteVideoRequest request = new DeleteVideoRequest();
            //*创建响应对象（不同操作，类不同）
            //DeleteVideoResponse response = new DeleteVideoResponse();
            //*向请求中设置参数
            //支持传入多个视频ID，多个用逗号分隔
            StringJoiner videoIdListString = new StringJoiner(",");
            videoIdList.forEach(videoIdListString::add);
            request.setVideoIds(videoIdListString.toString());
            //*调用客户端对象方法发送请求，拿到响应
            client.getAcsResponse(request);
            return Result.ok();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GlobalException("批量删除视频失败");
        }
    }

    @ApiOperation(value = "根据视频id，获取视频播放凭证")
    @GetMapping("get_video_play_auth/{vid}")
    public Result getVideoPlayAuth(@PathVariable String vid) {
        try {
            //*初始化客户端对象
            DefaultAcsClient client = AliyunVodSDKUtil.initVodClient(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //*创建请求对象（不同操作，类不同）
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //*创建响应对象（不同操作，类不同）
            GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
            //*向请求中设置参数
            request.setVideoId(vid);
            // *调用客户端对象方法发送请求，拿到响应
            response = client.getAcsResponse(request);
            //*从响应中拿到数据
            String videoPlayAuth = response.getPlayAuth();
            return Result.ok().data("videoPlayAuth", videoPlayAuth);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GlobalException("获取视频失败，请联系系统管理员解决问题！");
        }
    }
}
