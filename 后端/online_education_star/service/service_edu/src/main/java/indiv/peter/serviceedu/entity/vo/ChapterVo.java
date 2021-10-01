package indiv.peter.serviceedu.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PeterRays
 * @date 2021/9/22
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    private List<VideoVo> child = new ArrayList<>();

}
