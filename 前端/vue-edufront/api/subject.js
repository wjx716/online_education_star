import request from '@/utils/request'

export default {
  // 获得所有课程科目的 树形数据结构
  getAllSubjectTree() {
    return request({
      url: `/service_edu/edu_subject/get_all_subject_tree`,
      method: 'get',
    })
  },
}
