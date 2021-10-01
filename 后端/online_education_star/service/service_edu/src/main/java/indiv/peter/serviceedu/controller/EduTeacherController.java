package indiv.peter.serviceedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.peter.commonutils.Result;
import indiv.peter.commonutils.ResultMessage;
import indiv.peter.servicebase.exception.GlobalException;
import indiv.peter.serviceedu.entity.EduTeacher;
import indiv.peter.serviceedu.entity.vo.EduTeacherQuery;
import indiv.peter.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-13
 */
@Api(tags = {"讲师管理"})
@RestController
@CrossOrigin
@RequestMapping("/service_edu/edu_teacher")
public class EduTeacherController {

    private final EduTeacherService eduTeacherService;

    @Autowired
    public EduTeacherController(EduTeacherService eduTeacherService) {
        this.eduTeacherService = eduTeacherService;
    }

    @ApiOperation(value = "获取所有讲师列表")
    @GetMapping("/get_all_teacher")
    public Result getAllTeacher() {
        /*
        try {
            int i = 10/0;
        }catch(Exception e) {
            throw new GlobalException(ResultMessage.GLOBAL_EXCEPTION);
        }
        */
        List<EduTeacher> list = eduTeacherService.list(null);
        Result result;
        if (list != null) {
            result = Result.ok().data("list", list);
        } else {
            result = Result.error();
        }
        return result;
    }

    @ApiOperation(value = "根据讲师ID，删除指定讲师")
    @DeleteMapping("/delete_teacher_by_id/{id}")
    public Result deleteTeacherById(@PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        return Result.ok().data("deleteResult", b);
    }

    @ApiOperation(value = "不带条件，分页查询讲师列表")
    @GetMapping("/get_teacher_page/{current}/{limit}")
    public Result getTeacherPage(@PathVariable Long current, @PathVariable Long limit) {
        Page<EduTeacher> page = new Page<>(current, limit);
        eduTeacherService.page(page, null);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        return Result.ok().data("list", records).data("total", total);
    }

    @ApiOperation(value = "带条件，分页查询讲师列表")
    @PostMapping("/get_teacher_page_by_query/{current}/{limit}")
    public Result getTeacherPageByQuery(@PathVariable Long current, @PathVariable Long limit, @RequestBody EduTeacherQuery eduTeacherQuery) {
        String name = eduTeacherQuery.getName();
        Integer level = eduTeacherQuery.getLevel();
        String begin = eduTeacherQuery.getBegin();
        String end = eduTeacherQuery.getEnd();

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.lambda().like(EduTeacher::getName, name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.lambda().eq(EduTeacher::getLevel, level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.lambda().ge(EduTeacher::getGmtCreate, begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.lambda().le(EduTeacher::getGmtCreate, end);
        }

        Page<EduTeacher> page = new Page<>(current, limit);
        eduTeacherService.page(page, wrapper);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        return Result.ok().data("list", records).data("total", total);

    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("/add_teacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean result = eduTeacherService.save(eduTeacher);
        if (result) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("/get_teacher_by_id/{id}")
    public Result getTeacherById(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Result.ok().data("eduTeacher", eduTeacher);
    }

    @ApiOperation(value = "修改讲师")
    @PostMapping("/update_teacher_by_id")
    public Result updateTeacherById(@RequestBody EduTeacher eduTeacher) {
        boolean update = eduTeacherService.updateById(eduTeacher);
        if (update) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}

