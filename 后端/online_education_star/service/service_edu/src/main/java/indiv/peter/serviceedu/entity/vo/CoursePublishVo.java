package indiv.peter.serviceedu.entity.vo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 课程发布的展示内容Vo
 * @author PeterRays
 * @date 2021/9/23
 */
@Data
public class CoursePublishVo {
    private String id;
    private String courseTitle;
    private BigDecimal coursePrice;
    private String courseCover;
    private Integer courseLessonNum;

    private String teacherName;

    private String parentSubjectTitle;
    private String childSubjectTitle;

}
