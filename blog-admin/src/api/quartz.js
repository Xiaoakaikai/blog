import request from '@/utils/request'

export function fetchList(params) {
  return request({
    url: '/job/list',
    method: 'get',
    params:params
  })
}
export function info(id) {
  return request({
    url: '/job/info',
    method: 'get',
    params:{
      jobId:id
    }
  })
}
export function add(data) {
  return request({
    url: '/job/add',
    method: 'post',
    data
  })
}
export function update(data) {
  return request({
    url: '/job/update',
    method: 'post',
    data
  })
}
export function remove(id) {
  return request({
    url: '/job/delete',
    method: 'get',
    params:{
      jobId:id
    }
  })
}
export function deleteBatch(data) {
  return request({
    url: '/job/deleteBatch',
    method: 'post',
    data
  })
}
export function change(data) {
  return request({
    url: '/job/change',
    method: 'post',
    data
  })
}
export function run(data) {
  return request({
    url: '/job/run',
    method: 'post',
    data
  })
}
