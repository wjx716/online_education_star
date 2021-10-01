package indiv.peter.serviceoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import indiv.peter.commonutils.Result;
import indiv.peter.commonutils.ResultMessage;
import indiv.peter.servicebase.exception.GlobalException;
import indiv.peter.serviceoss.service.FileService;
import indiv.peter.serviceoss.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author PeterRays
 * @date 2021/9/19
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(@RequestParam MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtil.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        // 创建的Bucket的名称
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        // 获取，并设置文件夹目录
        LocalDate nowDate = LocalDate.now();
        String fileDirectory =
                ConstantPropertiesUtil.IMAGE_DIRECTORY_NAME + "/" +
                        nowDate.getYear() + "/" +
                        nowDate.getMonthValue() + "/" +
                        nowDate.getDayOfMonth() + "/";
        // 获取上传文件的原始文件名
        String fileOriginalFilename = file.getOriginalFilename();
        // 获取当前日期时间，加在文件夹后，防止重名，并拼接文件名
        String fileName = fileDirectory + "DateTime_" +  LocalDateTime.now() + "_" + fileOriginalFilename;

        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 获取上传文件流
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient
            ossClient.shutdown();
            // 返回URL
            return "https://" + bucketName + "." + endpoint + "/" + fileName;

        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(ResultMessage.SERVICE_OSS_FILE_UPLOAD_FAILURE);
        }

    }

}
