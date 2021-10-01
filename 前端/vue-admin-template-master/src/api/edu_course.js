import request from '@/utils/request'

export default {
  // 添加课程基本信息
  addCourseInfo(courseInfoForm) {
    return request({
      url: `/service_edu/edu_course/add_course_info`,
      method: 'post',
      data: courseInfoForm
    })
  },
  // 修改课程基本信息
  updateCourseInfo(courseInfoForm) {
    return request({
      url: `/service_edu/edu_course/update_course_info`,
      method: 'post',
      data: courseInfoForm
    })
  },
  // 根据ID，获取课程信息
  getCourseInfoById(id) {
    return request({
      url: `/service_edu/edu_course/get_course_info_by_id/${id}`,
      method: 'get',
    })
  },
  // 根据课程id，查询课程发布信息
  getCoursePublishById(id) {
    return request({
      url: `/service_edu/edu_course/get_course_publish_by_id/${id}`,
      method: 'get',
    })
  },
  // 根据课程id，发布课程
  publishCourseById(id) {
    return request({
      url: `/service_edu/edu_course/publish_course_by_id/${id}`,
      method: 'put',
    })
  },
  // 获取课程列表
  getCourseList() {
    return request({
      url: `/service_edu/edu_course/get_course_list`,
      method: 'get',
    })
  },
  // 带条件，分页查询课程列表
  getCoursePageByQuery(current, limit, courseQuery) {
    return request({
      url: `/service_edu/edu_course/get_course_page_by_query/${current}/${limit}`,
      method: 'post',
      data: courseQuery
    })
  },
  // 根据ID，删除课程相关的数据
  deleteCourseRelatedById(id) {
    return request({
      url: `/service_edu/edu_course/delete_course_related_by_id/${id}`,
      method: 'get',
    })
  }
}
