package indiv.peter.serviceedu.controller;

import indiv.peter.commonutils.Result;
import indiv.peter.serviceedu.entity.EduChapter;
import indiv.peter.serviceedu.entity.vo.ChapterVo;
import indiv.peter.serviceedu.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 章节 前端控制器
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-22
 */
@Api(tags = {"章节管理"})
@CrossOrigin
@RestController
@RequestMapping("/service_edu/edu_chapter")
public class EduChapterController {

    private final EduChapterService eduChapterService;

    @Autowired
    public EduChapterController(EduChapterService eduChapterService) {
        this.eduChapterService = eduChapterService;
    }

    /**
     * 根据课程ID，查询课程对应的章节信息
     * 课程的 章节，包括 章节本身 + 章节下的每个小节的视频
     */
    @ApiOperation(value = "根据课程ID，查询课程对应的章节信息")
    @GetMapping("get_chapter_list_by_course_id/{courseId}")
    public Result getChapterListByCourseId(@PathVariable String courseId) {
        List<ChapterVo> chapterVoList = eduChapterService.getChapterListByCourseId(courseId);
        return Result.ok().data("chapterList", chapterVoList);
    }

    @ApiOperation(value = "添加章节")
    @PostMapping("add_chapter")
    public Result addChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.save(eduChapter);
        return Result.ok();
    }

    @ApiOperation(value = "根据章节id，删除章节")
    @DeleteMapping("delete_chapter_by_id/{id}")
    public Result deleteChapterById(@PathVariable String id) {
        eduChapterService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据章节id，查询章节")
    @GetMapping("get_chapter_by_id/{id}")
    public Result getChapterById(@PathVariable String id) {
        EduChapter eduChapter = eduChapterService.getById(id);
        return Result.ok().data("eduChapter", eduChapter);
    }

    @ApiOperation(value = "根据章节id，修改章节")
    @PostMapping("update_chapter_by_id")
    public Result updateChapterById(@RequestBody EduChapter eduChapter) {
        eduChapterService.updateById(eduChapter);
        return Result.ok();
    }
}

