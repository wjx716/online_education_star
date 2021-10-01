package indiv.peter.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import indiv.peter.commonutils.ResultMessage;
import indiv.peter.servicebase.exception.GlobalException;
import indiv.peter.serviceedu.entity.EduSubject;
import indiv.peter.serviceedu.entity.vo.ExcelSubjectField;
import indiv.peter.serviceedu.service.EduSubjectService;

/**
 * @author PeterRays
 * @date 2021/9/20
 */
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectField> {

    private EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(ExcelSubjectField excelSubjectField, AnalysisContext analysisContext) {
        if (excelSubjectField == null) {
            throw new GlobalException(ResultMessage.GLOBAL_EXCEPTION);
        }

        // 获得第一列：父科目 的名称title
        String parentSubjectTitleInExcel = excelSubjectField.getParentSubjectTitle();
        // 查询数据库，对应的科目数据
        EduSubject parentSubject = getExistingSubjectFromDataBase(parentSubjectTitleInExcel, "0");
        // 当 父科目 不存在数据库时，才执行操作，防止插入重复数据
        if (parentSubject == null) {
            parentSubject = new EduSubject();
            parentSubject.setParentId("0");
            parentSubject.setTitle(parentSubjectTitleInExcel);
            eduSubjectService.save(parentSubject);
        }

        // 获得第二列：子科目 的名称title
        String childSubjectTitleInExcel = excelSubjectField.getChildSubjectTitle();
        // 获得 子科目对应的 父科目 的ID
        String  parentSubjectId = parentSubject.getId();
        // 查询数据库，对应的科目数据
        EduSubject childSubject = getExistingSubjectFromDataBase(childSubjectTitleInExcel, parentSubjectId);
        // 当 子科目 不存在数据库时，才执行操作，防止插入重复数据
        if (childSubject == null) {
            childSubject = new EduSubject();
            childSubject.setParentId(parentSubjectId);
            childSubject.setTitle(childSubjectTitleInExcel);
            eduSubjectService.save(childSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    private EduSubject getExistingSubjectFromDataBase(String title, String parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(EduSubject::getParentId, parentId);
        wrapper.lambda().eq(EduSubject::getTitle, title);
        return eduSubjectService.getOne(wrapper);
    }
}
