package indiv.peter.serviceoss.controller;

import indiv.peter.commonutils.Result;
import indiv.peter.serviceoss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author PeterRays
 * @date 2021/9/19
 */
@RestController
@Api(tags = {"文件管理"})
@CrossOrigin
@RequestMapping("/service_oss/oss_file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload_file")
    public Result uploadFile(MultipartFile file) {
        // 1.获取文件
        // 2.调用接口，上传文件，获取Url
        String url = fileService.uploadFile(file);
        return Result.ok().data("url", url);
    }
}
