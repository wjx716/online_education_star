package indiv.peter.serviceedu.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author PeterRays
 * @date 2021/9/20
 */
@Data
public class ExcelSubjectField {
    @ExcelProperty(index = 0)
    private String parentSubjectTitle;

    @ExcelProperty(index = 1)
    private String childSubjectTitle;
}
