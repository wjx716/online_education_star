import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/service_edu/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/service_edu/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/service_edu/logout',
    method: 'post'
  })
}
