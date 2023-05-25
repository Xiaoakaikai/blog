import request from '@/utils/request'

export function fetchCategory(params) {
  return request({
    url: '/category/list',
    method: 'GET',
    params:params
  })
}
export function deleteBatch(data) {
  return request({
    url: '/category/deleteBatch',
    method: 'delete',
    data
  })
}
export function remove(id) {
  return request({
    url: '/category/delete',
    method: 'delete',
    params:{
      id:id
    }
  })
}
export function add(data) {
  return request({
    url: '/category/add',
    method: 'POST',
    data
  })
}
export function info(id) {
  return request({
    url: '/category/info',
    method: 'get',
    params:{
      id:id
    }
  })
}
export function update(data) {
  return request({
    url: '/category/update',
    method: 'put',
    data
  })
}
export function top(id) {
  return request({
    url: '/category/top',
    method: 'get',
    params:{
      id:id
    }
  })
}
