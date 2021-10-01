package indiv.peter.serviceedu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询课程的Vo
 * @author PeterRays
 * @date 2021/9/23
 */
@Data
public class CourseQuery {
    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "课程子科目ID")
    private String subjectId;

    @ApiModelProperty(value = "课程父科目ID")
    private String subjectParentId;

    @ApiModelProperty(value = "课程标题")
    private String title;
}
