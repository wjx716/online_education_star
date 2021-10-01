package indiv.peter.serviceoss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author PeterRays
 * @date 2021/9/19
 */
public interface FileService {
    /**
     * 调用 aliyun 的 OSS 的接口
     * @param file 上传的图片文件
     * @return 返回图片访问路径
     */
    String uploadFile(MultipartFile file);
}
