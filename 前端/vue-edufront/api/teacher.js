import request from '@/utils/request'

export default {
    
    // 前台，不带条件，分页查询讲师
    getTeacherPage(current, limit){
        return request({
            url: `/service_edu/api_edu_teacher/get_teacher_page/${current}/${limit}`,
            method: 'get'
          })
    },

    // 前台查询讲师详情，包括讲师对应的课程
    getTeacherById(id){
        return request({
            url: `/service_edu/api_edu_teacher/get_teacher_detail_by_id/${id}`,
            method: 'get'
          })
    },
}