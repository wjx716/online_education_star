package indiv.peter.serviceedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import indiv.peter.commonutils.Result;
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
@Api(tags = {"模拟登录"})
@RestController
@CrossOrigin
@RequestMapping("/service_edu")
public class EduLoginController {

    @ApiOperation(value = "模拟获得管理员权限")
    @PostMapping("/login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    @ApiOperation(value = "传递user信息，模拟登录操作")
    @GetMapping("/info")
    public Result info(){
        return Result.ok()
                .data("roles", "admin")
                .data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}

