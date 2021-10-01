package indiv.peter.serviceedu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 前台课程展示Vo
 * @author PeterRays
 * @date 2021/9/27
 */
@Data
public class CourseFilterVo {
    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "父科目id")
    private String subjectParentId;

    @ApiModelProperty(value = "子科目id")
    private String subjectChildId;

    @ApiModelProperty(value = "销量排序")
    private String buyCountSort;

    @ApiModelProperty(value = "最新时间排序")
    private String gmtCreateSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;
}
