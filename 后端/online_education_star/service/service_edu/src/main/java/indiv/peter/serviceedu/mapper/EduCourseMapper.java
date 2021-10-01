package indiv.peter.serviceedu.mapper;

import indiv.peter.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import indiv.peter.serviceedu.entity.vo.CourseDetailVo;
import indiv.peter.serviceedu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-21
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    /**
     * 根据课程id，查询课程发布信息
     * @param id 课程id
     * @return 前端发布确认的信息
     */
    CoursePublishVo getCoursePublishById(String id);

    /**
     * 前台查询课程详情，包括课程详情、简介、讲师信息
     * @param id 课程Id
     * @return 封装的查询结果
     */
    CourseDetailVo getCourseDetailById(String id);
}
