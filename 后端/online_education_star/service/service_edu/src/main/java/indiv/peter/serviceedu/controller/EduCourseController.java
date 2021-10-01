package indiv.peter.serviceedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.peter.commonutils.Result;
import indiv.peter.serviceedu.client.VodClient;
import indiv.peter.serviceedu.entity.EduChapter;
import indiv.peter.serviceedu.entity.EduCourse;
import indiv.peter.serviceedu.entity.EduCourseDescription;
import indiv.peter.serviceedu.entity.EduVideo;
import indiv.peter.serviceedu.entity.vo.CourseInfoForm;
import indiv.peter.serviceedu.entity.vo.CoursePublishVo;
import indiv.peter.serviceedu.entity.vo.CourseQuery;
import indiv.peter.serviceedu.service.EduChapterService;
import indiv.peter.serviceedu.service.EduCourseDescriptionService;
import indiv.peter.serviceedu.service.EduCourseService;
import indiv.peter.serviceedu.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-21
 */
@Api(tags = {"课程管理"})
@RestController
@CrossOrigin
@RequestMapping("/service_edu/edu_course")
public class EduCourseController {

    private final EduCourseService eduCourseService;
    private final EduChapterService eduChapterService;
    private final EduVideoService eduVideoService;
    private final EduCourseDescriptionService eduDescriptionService;
    private final VodClient vodClient;


    @Autowired
    public EduCourseController(EduCourseService eduCourseService, EduChapterService eduChapterService, EduVideoService eduVideoService, EduCourseDescriptionService eduDescriptionService, VodClient vodClient) {
        this.eduCourseService = eduCourseService;
        this.eduChapterService = eduChapterService;
        this.eduVideoService = eduVideoService;
        this.eduDescriptionService = eduDescriptionService;
        this.vodClient = vodClient;
    }

    @ApiOperation(value = "添加课程基础信息")
    @PostMapping("add_course_info")
    public Result addCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        String eduCourseId = eduCourseService.addCourseInfo(courseInfoForm);
        return Result.ok().data("eduCourseId", eduCourseId);
    }

    @ApiOperation(value = "修改课程基础信息")
    @PostMapping("update_course_info")
    public Result updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        eduCourseService.updateCourseInfo(courseInfoForm);
        return Result.ok();
    }

    @ApiOperation(value = "根据课程ID，获取课程信息")
    @GetMapping("get_course_info_by_id/{id}")
    public Result getCourseInfoById(@PathVariable String id) {
        CourseInfoForm courseInfo = eduCourseService.getCourseInfoById(id);
        return Result.ok().data("courseInfo", courseInfo);
    }

    @ApiOperation(value = "根据课程id，查询课程发布信息")
    @GetMapping("get_course_publish_by_id/{id}")
    public Result getCoursePublishById(@PathVariable String id) {
        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublishById(id);
        return Result.ok().data("coursePublishVo", coursePublishVo);
    }

    @ApiOperation(value = "根据课程id，发布课程")
    @PutMapping("publish_course_by_id/{id}")
    public Result publishCourseById(@PathVariable String id){
        EduCourse eduCourse = eduCourseService.getById(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return Result.ok();
    }

    @ApiOperation(value = "获取课程列表(不带条件，不分页)")
    @GetMapping("get_course_list")
    public Result getCourseList() {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(EduCourse::getStatus, "Normal");
        List<EduCourse> courseList = eduCourseService.list(queryWrapper);
        return Result.ok().data("courseList", courseList);
    }

    @ApiOperation(value = "带条件，分页查询课程列表")
    @PostMapping("get_course_page_by_query/{current}/{limit}")
    public Result getCoursePageByQuery(@PathVariable Long current, @PathVariable Long limit, @RequestBody CourseQuery courseQuery) {
        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(EduCourse::getStatus, "Normal");
        queryWrapper.lambda().orderByAsc(EduCourse::getGmtModified);

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.lambda().like(EduCourse::getTitle, title);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            queryWrapper.lambda().eq(EduCourse::getTeacherId, teacherId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.lambda().eq(EduCourse::getSubjectParentId, subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.lambda().eq(EduCourse::getSubjectId, subjectId);
        }

        Page<EduCourse> page = new Page<>(current, limit);
        eduCourseService.page(page, queryWrapper);
        List<EduCourse> records = page.getRecords();
        long total = page.getTotal();
        return Result.ok().data("list", records).data("total", total);
    }

    @ApiOperation(value = "根据ID，删除课程相关的数据")
    @GetMapping("delete_course_related_by_id/{id}")
    public Result deleteCourseRelatedById(@PathVariable String id){
        //删除顺序：删除 阿里云上的视频、删除小节视频、删除章节、删除课程描述、删除课程
        // 0. 删除 阿里云上的视频
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.lambda().eq(EduVideo::getCourseId, id);
        videoWrapper.select("video_source_id");
        List<EduVideo> videoList = eduVideoService.list(videoWrapper);
        ArrayList<String> videoIdList = new ArrayList<>();
        videoList.forEach(video -> {
            videoIdList.add(video.getVideoSourceId());
        });
        if (!videoIdList.isEmpty()) {
            vodClient.deleteVideoList(videoIdList);
        }


        // 1. 删除小节视频
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.lambda().eq(EduVideo::getCourseId, id);
        eduVideoService.remove(videoQueryWrapper);

        // 2. 删除章节
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.lambda().eq(EduChapter::getCourseId, id);
        eduChapterService.remove(chapterQueryWrapper);

        // 3. 删除课程描述
        QueryWrapper<EduCourseDescription> descriptionQueryWrapper = new QueryWrapper<>();
        descriptionQueryWrapper.lambda().eq(EduCourseDescription::getId, id);
        eduDescriptionService.remove(descriptionQueryWrapper);

        // 4. 删除课程
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.lambda().eq(EduCourse::getId, id);
        eduCourseService.remove(courseQueryWrapper);

        return Result.ok();
    }
}

