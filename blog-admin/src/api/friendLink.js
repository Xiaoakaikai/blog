import request from '@/utils/request'

export function fetchList(params) {
  return request({
    url: '/friend/list',
    method: 'get',
    params:params
  })
}
export function update(data) {
  return request({
    url: '/friend/update',
    method: 'put',
    data
  })
}
export function create(data) {
  return request({
    url: '/friend/create',
    method: 'post',
    data
  })
}
export function remove(data) {
  return request({
    url: '/friend/remove',
    method: 'delete',
    data
  })
}
export function top(id) {
  return request({
    url: '/friend/top',
    method: 'get',
    params: {
      id:id
    }
  })
}
