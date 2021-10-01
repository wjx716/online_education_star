package indiv.peter.serviceedu.service;

import indiv.peter.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import indiv.peter.serviceedu.entity.vo.ParentSubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-20
 */
public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 从Excel文件，导入课程科目
     * @param file 上传的Excel文件
     * @param eduSubjectService 课程科目的Service
     */
    void importSubjectFromExcel(MultipartFile file, EduSubjectService eduSubjectService);

    /**
     * 获得所有课程科目的 树形数据结构
     * @return 父科目的集合，包含了子科目
     */
    List<ParentSubjectVo> getAllSubjectTree();
}
