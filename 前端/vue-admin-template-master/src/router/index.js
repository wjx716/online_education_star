import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/index',
    meta: { title: '首页'},
    name: '首页',
    children: [
      {
        path: '/index',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '首页', icon: 'index' }
      }
    ]
  },

  {
    path: '/edu_teacher',
    component: Layout,
    redirect: '/edu_teacher/teacher_list',
    name: '讲师管理',
    meta: { title: '讲师管理', icon: 'user' },
    children: [
      {
        path: 'teacher_list',
        name: '讲师列表',
        component: () => import('@/views/edu_teacher/list'),
        meta: { title: '讲师列表', icon: 'list' }
      },
      {
        path: 'teacher_add',
        name: '添加讲师',
        component: () => import('@/views/edu_teacher/add'),
        meta: { title: '添加讲师', icon: 'add' }
      },
      {
        path: 'teacher_update/:id',
        name: '修改讲师',
        hidden: true,
        component: () => import('@/views/edu_teacher/add'),
        meta: { title: '修改讲师', icon: 'edit', noCache: true }
      },
    ]
  },

  {
    path: '/edu_subject',
    component: Layout,
    redirect: '/edu_subject/subject_add',
    name: '科目管理',
    meta: { title: '科目管理', icon: 'subject' },
    children: [
      {
        path: 'subject_add',
        name: '导入科目',
        component: () => import('@/views/edu_subject/add'),
        meta: { title: '导入科目', icon: 'import' }
      },
      {
        path: 'subject_list',
        name: '科目展示',
        component: () => import('@/views/edu_subject/list'),
        meta: { title: '科目展示', icon: 'category' }
      },
    ]
  },

  {
    path: '/edu_course',
    component: Layout,
    redirect: '/edu_course/course_list',
    name: '课程管理',
    meta: { title: '课程管理', icon: 'course' },
    children: [
      {
        path: 'course_list',
        name: '课程列表',
        component: () => import('@/views/edu_course/list'),
        meta: { title: '课程列表', icon: 'list' }
      },
      {
        path: 'course_add',
        name: '课程发布',
        component: () => import('@/views/edu_course/add'),
        meta: { title: '课程发布', icon: 'edit' }
      },
      // 隐藏页面
      {
        path: 'course_add/:id',
        name: '填写课程基本信息',
        component: () => import('@/views/edu_course/add'),
        meta: { title: '填写课程基本信息', noCache: true },
        hidden: true
      },
      {
        path: 'course_chapter/:id',
        name: '创建课程大纲',
        component: () => import('@/views/edu_course/chapter'),
        meta: { title: '创建课程大纲', noCache: true },
        hidden: true
      },
      {
        path: 'course_publish/:id',
        name: '确认课程发布',
        component: () => import('@/views/edu_course/publish'),
        meta: { title: '确认课程发布', noCache: true },
        hidden: true
      }
    ]
  },

  // {
  //   path: '/form',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'Form',
  //       component: () => import('@/views/form/index'),
  //       meta: { title: 'Form', icon: 'form' }
  //     }
  //   ]
  // },

  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'Nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: () => import('@/views/nested/menu1/index'), // Parent router-view
  //       name: 'Menu1',
  //       meta: { title: 'Menu1' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       component: () => import('@/views/nested/menu2/index'),
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },

  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
  //       meta: { title: 'External Link', icon: 'user' }
  //     }
  //   ]
  // },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
