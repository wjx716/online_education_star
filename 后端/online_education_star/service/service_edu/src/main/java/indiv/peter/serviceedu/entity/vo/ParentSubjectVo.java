package indiv.peter.serviceedu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PeterRays
 * @date 2021/9/20
 */
@Data
public class ParentSubjectVo {
    @ApiModelProperty(value = "课程类别ID")
    private String id;

    @ApiModelProperty(value = "类别名称")
    private String title;

    @ApiModelProperty(value = "课程子科目集合")
    private List<ChildSubjectVo> child = new ArrayList<>();
}
