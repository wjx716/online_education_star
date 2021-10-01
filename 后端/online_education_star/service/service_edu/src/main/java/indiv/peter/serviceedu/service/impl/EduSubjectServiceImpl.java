package indiv.peter.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import indiv.peter.commonutils.ResultMessage;
import indiv.peter.servicebase.exception.GlobalException;
import indiv.peter.serviceedu.entity.EduSubject;
import indiv.peter.serviceedu.entity.vo.ChildSubjectVo;
import indiv.peter.serviceedu.entity.vo.ExcelSubjectField;
import indiv.peter.serviceedu.entity.vo.ParentSubjectVo;
import indiv.peter.serviceedu.listener.SubjectExcelListener;
import indiv.peter.serviceedu.mapper.EduSubjectMapper;
import indiv.peter.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author PeterRays
 * @since 2021-09-20
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Override
    public void importSubjectFromExcel(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream fileInputStream = file.getInputStream();
            EasyExcel.read(fileInputStream, ExcelSubjectField.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(ResultMessage.GLOBAL_EXCEPTION);
        }
    }

    @Override
    public List<ParentSubjectVo> getAllSubjectTree() {
        //TODO: 优化方案，如果数据量太大，可以将List转换为Map，避免取For循环，而是Hash值定位

        // 获取所有 父科目
        QueryWrapper<EduSubject> parentQueryWrapper = new QueryWrapper<>();
        parentQueryWrapper.lambda().eq(EduSubject::getParentId, "0");
        List<EduSubject> parentSubjectList = list(parentQueryWrapper);

        // 获取所有 子科目
        QueryWrapper<EduSubject> childQueryWrapper = new QueryWrapper<>();
        childQueryWrapper.lambda().ne(EduSubject::getParentId, "0");
        List<EduSubject> childSubjectList = list(childQueryWrapper);

        // 定义 父科目VoList
        ArrayList<ParentSubjectVo> parentSubjectVoList = new ArrayList<>();

        // 遍历 父科目
        for (EduSubject parentSubject : parentSubjectList) {
            // 取其中需要的属性，放进 父科目VoList
            ParentSubjectVo parentSubjectVo = new ParentSubjectVo();
            BeanUtils.copyProperties(parentSubject, parentSubjectVo);

            // 定义 子科目VoList
            ArrayList<ChildSubjectVo> childSubjectVoList = new ArrayList<>();

            // 对每一个父科目，遍历子科目
            for (EduSubject childSubject : childSubjectList) {
                // 找到具有 父-子 关系的科目，将其子科目，放进 父科目VoList
                if (childSubject.getParentId().equals(parentSubject.getId())) {
                    ChildSubjectVo childSubjectVo = new ChildSubjectVo();
                    BeanUtils.copyProperties(childSubject, childSubjectVo);
                    childSubjectVoList.add(childSubjectVo);
                }
            }
            // 将 子科目VoList，set 到 ParentSubjectVo
            parentSubjectVo.setChild(childSubjectVoList);

            parentSubjectVoList.add(parentSubjectVo);
        }

        // 返回
        return parentSubjectVoList;
    }
}
