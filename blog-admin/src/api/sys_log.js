import request from '@/utils/request'

export function fetchUserLog(params) {
  return request({
    url: '/userLog/list',
    method: 'get',
    params
  })
}
export function deleteUserLog(data) {
  return request({
    url: '/userLog/delete',
    method: 'delete',
    data
  })
}
export function fetchAdminLog(params) {
  return request({
    url: '/adminLog/list',
    method: 'get',
    params
  })
}
export function deleteAdminLog(data) {
  return request({
    url: '/adminLog/delete',
    method: 'delete',
    data
  })
}
export function fetchExceptionLog(params) {
  return request({
    url: '/exceptionLog/list',
    method: 'get',
    params
  })
}
export function deleteExceptionLog(data) {
  return request({
    url: '/exceptionLog/delete',
    method: 'delete',
    data
  })
}
