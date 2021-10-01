package indiv.peter.serviceedu.api;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.peter.commonutils.Result;
import indiv.peter.serviceedu.client.VodClient;
import indiv.peter.serviceedu.entity.EduCourse;
import indiv.peter.serviceedu.entity.vo.ChapterVo;
import indiv.peter.serviceedu.entity.vo.CourseDetailVo;
import indiv.peter.serviceedu.entity.vo.CourseFilterVo;
import indiv.peter.serviceedu.service.EduChapterService;
import indiv.peter.serviceedu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author PeterRays
 * @date 2021/9/27
 */
@Api(tags= {"前台课程展示"})
@RestController
@RequestMapping("/service_edu/api_edu_course")
@CrossOrigin
public class CourseApiController {
    private final EduCourseService eduCourseService;
    private final EduChapterService eduChapterService;


    @Autowired
    public CourseApiController(EduCourseService eduCourseService, EduChapterService eduChapterService, VodClient vodClient) {
        this.eduCourseService = eduCourseService;
        this.eduChapterService = eduChapterService;
    }

    @ApiOperation(value = "前台，带条件，分页查询课程")
    @PostMapping("/get_course_page/{current}/{limit}")
    public Result getCoursePage(@PathVariable Long current, @PathVariable Long limit, @RequestBody CourseFilterVo courseFilterVo) {
        Page<EduCourse> page = new Page<>(current, limit);
        Map<String, Object> map = eduCourseService.getCoursePage(page, courseFilterVo);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "前台查询课程详情，包括课程详情、简介、讲师信息 + 章节+小节视频信息")
    @GetMapping("/get_course_detail_by_id/{id}")
    public Result getCourseDetailById(@PathVariable String id) {
        // 根据ID，获得课程对应的 课程详情、简介、讲师信息
        CourseDetailVo courseDetail = eduCourseService.getCourseDetailById(id);
        // 根据ID，获得课程对应的 章节+小节视频信息
        List<ChapterVo> chapterVoList = eduChapterService.getChapterListByCourseId(id);
        return Result.ok().data("courseDetail", courseDetail).data("chapterVoList", chapterVoList);
    }
}
