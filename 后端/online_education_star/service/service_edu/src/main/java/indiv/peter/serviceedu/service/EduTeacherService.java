package indiv.peter.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.peter.serviceedu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-13
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 前台，不带条件，分页查询讲师
     * @param page page对象
     * @return 查询结果的map
     */
    Map<String, Object> getTeacherPage(Page<EduTeacher> page);
}
