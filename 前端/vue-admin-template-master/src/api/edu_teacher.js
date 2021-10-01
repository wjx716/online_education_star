import request from '@/utils/request'

export default {
  // 获取讲师列表
  getTeacherPageByQuery(current, limit, eduTeacherQuery) {
    return request({
      url: `/service_edu/edu_teacher/get_teacher_page_by_query/${current}/${limit}`,
      method: 'post',
      data: eduTeacherQuery // 转化json传递
    })
  },
  // 根据ID，删除讲师
  deleteTeacherById(id) {
    return request({
      url: `/service_edu/edu_teacher/delete_teacher_by_id/${id}`,
      method: 'delete'
    })
  },
  // 添加讲师
  addTeacher(teacher) {
    return request({
      url: `/service_edu/edu_teacher/add_teacher`,
      method: 'post',
      data: teacher
    })
  },
  // 修改讲师
  updateTeacherById(teacher) {
    return request({
      url: `/service_edu/edu_teacher/update_teacher_by_id`,
      method: 'post',
      data: teacher
    })
  },
  // 根据ID，获取讲师
  getTeacherById(id) {
    return request({
      url: `/service_edu/edu_teacher/get_teacher_by_id/${id}`,
      method: 'get'
    })
  },
  // 获得所有讲师信息
  getAllTeacher() {
    return request({
      url: `/service_edu/edu_teacher/get_all_teacher`,
      method: 'get'
    })
  }
}
