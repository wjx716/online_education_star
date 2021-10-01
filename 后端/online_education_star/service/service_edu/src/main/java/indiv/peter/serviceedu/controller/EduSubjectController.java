package indiv.peter.serviceedu.controller;


import indiv.peter.commonutils.Result;
import indiv.peter.serviceedu.entity.vo.ParentSubjectVo;
import indiv.peter.serviceedu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-20
 */
@Api(tags = {"科目管理"})
@RestController
@CrossOrigin
@RequestMapping("/service_edu/edu_subject")
public class EduSubjectController {

    private final EduSubjectService eduSubjectService;

    @Autowired
    public EduSubjectController(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @ApiOperation(value = "从Excel文件，导入课程科目")
    @PostMapping("import_subject_from_excel")
    public Result importSubjectFromExcel(MultipartFile file){
        eduSubjectService.importSubjectFromExcel(file, eduSubjectService);
        return Result.ok();
    }

    @ApiOperation(value = "获得所有课程科目的 树形数据结构")
    @GetMapping("get_all_subject_tree")
    public Result getAllSubjectTree() {
        List<ParentSubjectVo> allSubjectTree = eduSubjectService.getAllSubjectTree();
        return Result.ok().data("allSubjectTree", allSubjectTree);
    }
}

