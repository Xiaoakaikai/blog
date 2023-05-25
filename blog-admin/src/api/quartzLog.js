import request from '@/utils/request'

export function fetchList(params) {
  return request({
    url: '/jobLog/list',
    method: 'get',
    params:params
  })
}
export function deleteBatch(data) {
  return request({
    url: '/jobLog/deleteBatch',
    method: 'post',
    data
  })
}
export function clean() {
  return request({
    url: '/jobLog/clean',
    method: 'get',
    params: {}
  })
}
