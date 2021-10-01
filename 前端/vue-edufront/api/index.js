import request from '@/utils/request'

export default {
    
    //首页展示8条课程信息4条讲师信息
    getCourseTeacherList(){
        return request({
            url: `/service_edu/api_edu_index/getCourseTeacherList`,
            method: 'get'
          })
    }
}