import request from '@/utils/request'

export function fetchTags(params) {
  return request({
    url: '/tags/list',
    method: 'GET',
    params:params
  })
}
export function remove(id) {
  return request({
    url: '/tags/remove',
    method: 'delete',
    params:{
      id:id
    }
  })
}
export function deleteBatch(data) {
  return request({
    url: '/tags/deleteBatch',
    method: 'delete',
    data
  })
}
export function add(data) {
  return request({
    url: '/tags/add',
    method: 'POST',
    data
  })
}
export function info(id) {
  return request({
    url: '/tags/info',
    method: 'get',
    params:{
      id:id
    }
  })
}
export function update(data) {
  return request({
    url: '/tags/update',
    method: 'put',
    data
  })
}
export function top(id) {
  return request({
    url: '/tags/top',
    method: 'get',
    params:{
      id:id
    }
  })
}
