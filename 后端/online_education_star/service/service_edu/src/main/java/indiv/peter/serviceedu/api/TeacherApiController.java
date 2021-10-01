package indiv.peter.serviceedu.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.peter.commonutils.Result;
import indiv.peter.serviceedu.entity.EduCourse;
import indiv.peter.serviceedu.entity.EduTeacher;
import indiv.peter.serviceedu.entity.vo.EduTeacherQuery;
import indiv.peter.serviceedu.service.EduCourseService;
import indiv.peter.serviceedu.service.EduTeacherService;
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
@Api(tags= {"前台讲师展示"})
@RestController
@RequestMapping("/service_edu/api_edu_teacher")
@CrossOrigin
public class TeacherApiController {

    private final EduTeacherService eduTeacherService;
    private final EduCourseService eduCourseService;

    @Autowired
    public TeacherApiController(EduTeacherService eduTeacherService, EduCourseService eduCourseService) {
        this.eduTeacherService = eduTeacherService;
        this.eduCourseService = eduCourseService;
    }

    @ApiOperation(value = "前台，不带条件，分页查询讲师")
    @GetMapping("/get_teacher_page/{current}/{limit}")
    public Result getTeacherPage(@PathVariable Long current, @PathVariable Long limit) {
        Page<EduTeacher> page = new Page<>(current, limit);
        Map<String, Object> map = eduTeacherService.getTeacherPage(page);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "前台查询讲师详情，包括讲师对应的课程")
    @GetMapping("/get_teacher_detail_by_id/{id}")
    public Result getTeacherDetailById(@PathVariable String id) {
        // 根据ID，获得讲师
        EduTeacher teacher = eduTeacherService.getById(id);
        // 根据ID，获得讲师所讲授的课程
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(EduCourse::getTeacherId, id);
        queryWrapper.lambda().eq(EduCourse::getStatus, "Normal");
        List<EduCourse> courseList = eduCourseService.list(queryWrapper);

        return Result.ok().data("teacher", teacher).data("courseList", courseList);
    }
}
