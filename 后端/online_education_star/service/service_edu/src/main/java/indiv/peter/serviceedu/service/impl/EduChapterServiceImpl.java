package indiv.peter.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import indiv.peter.serviceedu.entity.EduChapter;
import indiv.peter.serviceedu.entity.EduVideo;
import indiv.peter.serviceedu.entity.vo.ChapterVo;
import indiv.peter.serviceedu.entity.vo.VideoVo;
import indiv.peter.serviceedu.mapper.EduChapterMapper;
import indiv.peter.serviceedu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indiv.peter.serviceedu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 章节 服务实现类
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-22
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    private final EduVideoService eduVideoService;

    @Autowired
    public EduChapterServiceImpl(EduVideoService eduVideoService) {
        this.eduVideoService = eduVideoService;
    }

    @Override
    public List<ChapterVo> getChapterListByCourseId(String courseId) {
        // 查询得到 chapterList
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.lambda().eq(EduChapter::getCourseId, courseId);
        chapterQueryWrapper.lambda().orderByDesc(EduChapter::getSort);
        chapterQueryWrapper.lambda().orderByAsc(EduChapter::getGmtCreate);
        List<EduChapter> chapterList = list(chapterQueryWrapper);

        // 查询得到 videoList
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.lambda().eq(EduVideo::getCourseId, courseId);
        videoQueryWrapper.lambda().orderByDesc(EduVideo::getSort);
        videoQueryWrapper.lambda().orderByAsc(EduVideo::getGmtCreate);
        List<EduVideo> videoList = eduVideoService.list(videoQueryWrapper);

        // 创建返回前端的 chapterVoList
        List<ChapterVo> chapterVoList = new ArrayList<>();

        // 为 chapterList 的每一个 chapter
        // 查询这个 chapter 的 id 对应的 所有video
        // 将这些 video 放进 videoVoList，
        // 再将 videoVoList 放进 chapterVoList 的 每个 chapterVo
        chapterList.forEach(chapter -> {
            // 创建 chapterVoList 的 每个 chapter 的 child —> videoVoList
            List<VideoVo> videoVoList = new ArrayList<>();
            videoList.forEach(video -> {
                if (video.getChapterId().equals(chapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            });
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVo.setChild(videoVoList);
            chapterVoList.add(chapterVo);
        });
        return chapterVoList;
    }
}
