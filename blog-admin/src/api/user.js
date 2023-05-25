import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}
export function getInfo() {
  return request({
    url: '/user/getCurrentUserInfo',
    method: 'post',
    params: {}
  })
}
export function logout() {
  return request({
    url: '/logout',
    method: 'get'
  })
}
export function captchaImage() {
  return request({
    url: '/captchaImage',
    method: 'get',
    params:{}
  })
}
export function fetchUser(params) {
  return request({
    url: '/user/list',
    method: 'get',
    params:params
  })
}
export function remove(data) {
  return request({
    url: '/user/remove',
    method: 'delete',
    data
  })
}
export function create(data) {
  return request({
    url: '/user/create',
    method: 'post',
    data
  })
}
export function update(data) {
  return request({
    url: '/user/update',
    method: 'put',
    data
  })
}
export function info(id) {
  return request({
    url: '/user/info',
    method: 'get',
    params:{id:id}
  })
}
export function getMenuTree() {
  return request({
    url: '/user/getUserMenu',
    method: 'post',
    params:{}
  })
}
export function editPassword(data) {
  return request({
    url: '/user/updatePassword',
    method: 'post',
    data
  })
}

export function onlineUser(params) {
  return request({
    url: '/user/online',
    method: 'get',
    params:params
  })
}
export function kick(params) {
  return request({
    url: '/user/kick',
    method: 'get',
    params:params
  })
}
