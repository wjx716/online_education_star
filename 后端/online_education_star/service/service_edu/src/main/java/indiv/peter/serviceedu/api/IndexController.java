package indiv.peter.serviceedu.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import indiv.peter.commonutils.Result;
import indiv.peter.serviceedu.entity.EduCourse;
import indiv.peter.serviceedu.entity.EduTeacher;
import indiv.peter.serviceedu.service.EduCourseService;
import indiv.peter.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author PeterRays
 * @date 2021/9/26
 */
@Api(tags= {"首页显示"})
@RestController
@RequestMapping("/service_edu/api_edu_index")
@CrossOrigin
public class IndexController {

    private final EduCourseService eduCourseService;
    private final EduTeacherService eduTeacherService;

    @Autowired
    public IndexController(EduCourseService eduCourseService, EduTeacherService eduTeacherService) {
        this.eduCourseService = eduCourseService;
        this.eduTeacherService = eduTeacherService;
    }

    @ApiOperation(value = "首页展示8条课程信息4条讲师信息")
    @GetMapping("getCourseTeacherList")
    public Result getCourseTeacherList(){
        //8条课程信息 最新
        QueryWrapper<EduCourse> courseWrapper = new QueryWrapper<>();
        courseWrapper.lambda().eq(EduCourse::getStatus, "Normal");
        courseWrapper.lambda().orderByDesc(EduCourse::getGmtCreate);
        courseWrapper.last("LIMIT 8");
        List<EduCourse> courseList = eduCourseService.list(courseWrapper);

        //4条讲师信息 最新
        QueryWrapper<EduTeacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.lambda().orderByDesc(EduTeacher::getGmtCreate);
        teacherWrapper.last("LIMIT 4");
        List<EduTeacher> teacherList = eduTeacherService.list(teacherWrapper);
        return Result.ok().data("courseList",courseList).data("teacherList",teacherList);

    }
}
