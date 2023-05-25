import request from '@/utils/request'

export function fetchPhoto(params) {
  return request({
    url: '/photo/list',
    method: 'get',
    params
  })
}
export function infoPhoto(id) {
  return request({
    url: '/photo/info',
    method: 'get',
    params:{
      id:id
    }
  })
}
export function addPhoto(data) {
  return request({
    url: '/photo/add',
    method: 'post',
    data
  })
}
export function updatePhoto(data) {
  return request({
    url: '/photo/update',
    method: 'put',
    data
  })
}
export function deleteBatch(data) {
  return request({
    url: '/photo/deleteBatch',
    method: 'delete',
    data
  })
}
export function movePhoto(data) {
  return request({
    url: '/photo/movePhoto',
    method: 'post',
    data
  })
}

