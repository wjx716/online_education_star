package indiv.peter.serviceedu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author PeterRays
 * @date 2021/9/20
 */
@Data
public class ChildSubjectVo {
    @ApiModelProperty(value = "课程类别ID")
    private String id;

    @ApiModelProperty(value = "类别名称")
    private String title;
}
