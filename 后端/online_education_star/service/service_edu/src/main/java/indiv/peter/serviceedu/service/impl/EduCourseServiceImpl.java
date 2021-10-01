package indiv.peter.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indiv.peter.commonutils.ResultMessage;
import indiv.peter.servicebase.exception.GlobalException;
import indiv.peter.serviceedu.entity.EduCourse;
import indiv.peter.serviceedu.entity.EduCourseDescription;
import indiv.peter.serviceedu.entity.vo.CourseDetailVo;
import indiv.peter.serviceedu.entity.vo.CourseFilterVo;
import indiv.peter.serviceedu.entity.vo.CourseInfoForm;
import indiv.peter.serviceedu.entity.vo.CoursePublishVo;
import indiv.peter.serviceedu.mapper.EduCourseMapper;
import indiv.peter.serviceedu.service.EduCourseDescriptionService;
import indiv.peter.serviceedu.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-21
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    private final EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    public EduCourseServiceImpl(EduCourseDescriptionService eduCourseDescriptionService) {
        this.eduCourseDescriptionService = eduCourseDescriptionService;
    }

    @Override
    public String addCourseInfo(CourseInfoForm courseInfoForm) {
        // 先把数据插入 edu_course 表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        boolean saveResult = save(eduCourse);
        if (!saveResult) {
            throw new GlobalException(ResultMessage.SAVE_ENTITY_FAILURE);
        }

        // 获得 添加数据的ID
        String eduCourseId = eduCourse.getId();

        // 再把简介数据插入 edu_course_description 表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(eduCourseId);
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescriptionService.save(eduCourseDescription);

        return eduCourseId;
    }

    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {
        // 先把数据插入 edu_course 表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        boolean updateResult = updateById(eduCourse);
        if (!updateResult) {
            throw new GlobalException(ResultMessage.SAVE_ENTITY_FAILURE);
        }
        // 再把简介数据插入 edu_course_description 表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public CourseInfoForm getCourseInfoById(String eduCourseId) {
        // 根据ID，获得课程基础信息
        EduCourse eduCourse = getById(eduCourseId);
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse, courseInfoForm);

        // 根据ID，获得课程简介
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(eduCourseId);
        courseInfoForm.setDescription(courseDescription.getDescription());

        return courseInfoForm;
    }

    @Override
    public CoursePublishVo getCoursePublishById(String id) {
        return baseMapper.getCoursePublishById(id);
    }

    @Override
    public Map<String, Object> getCoursePage(Page<EduCourse> page, CourseFilterVo courseFilterVo) {

        // 1. 取出查询条件
        String subjectParentId = courseFilterVo.getSubjectParentId();
        String subjectChildId = courseFilterVo.getSubjectChildId();
        String buyCountSort = courseFilterVo.getBuyCountSort();
        String gmtCreateSort = courseFilterVo.getGmtCreateSort();
        String priceSort = courseFilterVo.getPriceSort();

        // 2. 封装查询条件
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(EduCourse::getStatus, "Normal");
        // 2.1 科目分类
        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.lambda().eq(EduCourse::getSubjectParentId, subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectChildId)) {
            queryWrapper.lambda().eq(EduCourse::getSubjectId, subjectChildId);
        }
        // 2.2 排序
        if (!StringUtils.isEmpty(buyCountSort)) {
            queryWrapper.lambda().orderByDesc(EduCourse::getBuyCount);
        }
        if (!StringUtils.isEmpty(gmtCreateSort)) {
            queryWrapper.lambda().orderByDesc(EduCourse::getGmtCreate);
        }
        if (!StringUtils.isEmpty(priceSort)) {
            queryWrapper.lambda().orderByDesc(EduCourse::getPrice);
        }

        // 3. 分页查询
        baseMapper.selectPage(page, queryWrapper);

        // 4. 取出查询结果
        List<EduCourse> records = page.getRecords();
        long total = page.getTotal();
        long current = page.getCurrent();
        long size = page.getSize();
        long pages = page.getPages();
        boolean hasPrevious = page.hasPrevious();
        boolean hasNext = page.hasNext();

        // 5. 封装结果
        HashMap<String, Object> map = new HashMap<>(8);
        map.put("items", records);
        map.put("total", total);
        map.put("current", current);
        map.put("size", size);
        map.put("pages", pages);
        map.put("hasPrevious", hasPrevious);
        map.put("hasNext", hasNext);

        return map;
    }

    @Override
    public CourseDetailVo getCourseDetailById(String id) {
        return baseMapper.getCourseDetailById(id);
    }
}
