import request from '@/utils/request'

export default {
  // 根据课程ID，查询课程对应的章节信息
  getChapterListByCourseId(courseId) {
    return request({
      url: `/service_edu/edu_chapter/get_chapter_list_by_course_id/${courseId}`,
      method: 'get'
    })
  },
  // 添加章节
  addChapter(eduChapter) {
    return request({
      url: `/service_edu/edu_chapter/add_chapter`,
      method: 'post',
      data: eduChapter
    })
  },
  // 根据章节id，删除章节
  deleteChapterById(id) {
    return request({
      url: `/service_edu/edu_chapter/delete_chapter_by_id/${id}`,
      method: 'delete'
    })
  },
  // 根据章节id，查询章节
  getChapterById(id) {
    return request({
      url: `/service_edu/edu_chapter/get_chapter_by_id/${id}`,
      method: 'get'
    })
  },
  // 根据章节id，修改章节
  updateChapterById(eduChapter) {
    return request({
      url: `/service_edu/edu_chapter/update_chapter_by_id`,
      method: 'post',
      data: eduChapter
    })
  }
}
