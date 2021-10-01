package indiv.peter.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.peter.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import indiv.peter.serviceedu.entity.vo.CourseDetailVo;
import indiv.peter.serviceedu.entity.vo.CourseFilterVo;
import indiv.peter.serviceedu.entity.vo.CourseInfoForm;
import indiv.peter.serviceedu.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-21
 */
public interface EduCourseService extends IService<EduCourse> {
    /**
     * 添加课程信息
     * @param courseInfoForm 前端返回的，添加课程的对象
     * @return 返回数据库的课程ID
     */
    String addCourseInfo(CourseInfoForm courseInfoForm);

    /**
     * 根据ID，获取课程信息
     * @param eduCourseId 课程ID
     * @return 返回的课程信息
     */
    CourseInfoForm getCourseInfoById(String eduCourseId);

    /**
     * 修改课程信息
     * @param courseInfoForm 前端返回的，修改课程的对象
     */
    void updateCourseInfo(CourseInfoForm courseInfoForm);

    /**
     * 根据课程id，查询课程发布信息
     * @param id 课程id
     * @return 前端发布确认的信息
     */
    CoursePublishVo getCoursePublishById(String id);

    /**
     * 前台，带条件，分页查询课程
     * @param page page条件参数对象
     * @param courseFilterVo 用于排序的查询对象
     * @return Map封装的查询结果
     */
    Map<String, Object> getCoursePage(Page<EduCourse> page, CourseFilterVo courseFilterVo);

    /**
     * 前台查询课程详情，包括课程详情、简介、讲师信息
     * @param id 课程Id
     * @return 封装的查询结果
     */
    CourseDetailVo getCourseDetailById(String id);
}
