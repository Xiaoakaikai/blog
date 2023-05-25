import request from '@/utils/request'

export function fetchDictList(params) {
  return request({
    url: '/dict/list',
    method: 'get',
    params:params
  })
}
export function addDict(data) {
  return request({
    url: '/dict/add',
    method: 'post',
    data
  })
}
export function updateDict(data) {
  return request({
    url: '/dict/update',
    method: 'post',
    data
  })
}
export function deleteDict(id) {
  return request({
    url: '/dict/delete',
    method: 'delete',
    params:{
      id:id
    }
  })
}
export function deleteBatchDict(data) {
  return request({
    url: '/dict/deleteBatch',
    method: 'delete',
    data
  })
}

