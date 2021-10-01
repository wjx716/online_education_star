import request from "@/utils/request";

export default {
  // 前台，不带条件，分页查询讲师
  getCoursePage(current, limit, searchObj) {
    return request({
      url: `/service_edu/api_edu_course/get_course_page/${current}/${limit}`,
      method: "post",
      data: searchObj
    });
  },

  // 前台查询讲师详情，包括讲师对应的课程
  getCourseDetailById(id) {
    return request({
      url: `/service_edu/api_edu_course/get_course_detail_by_id/${id}`,
      method: "get"
    });
  }
};
