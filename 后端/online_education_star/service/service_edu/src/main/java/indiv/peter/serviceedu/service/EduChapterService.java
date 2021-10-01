package indiv.peter.serviceedu.service;

import indiv.peter.serviceedu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import indiv.peter.serviceedu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 章节 服务类
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-22
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 根据课程ID，查询课程对应的章节信息
     * 课程的 章节，包括 章节本身 + 章节下的每个小节的视频
     * @param courseId 课程ID
     * @return ID对应的 章节的List
     */
    List<ChapterVo> getChapterListByCourseId(String courseId);
}
