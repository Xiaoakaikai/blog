import request from '@/utils/request'

export function fetchAlbum(params) {
  return request({
    url: '/album/list',
    method: 'get',
    params
  })
}
export function info(id) {
  return request({
    url: '/album/info',
    method: 'get',
    params:{
      id:id
    }
  })
}
export function addAlbum(data) {
  return request({
    url: '/album/add',
    method: 'post',
    data
  })
}
export function updateAlbum(data) {
  return request({
    url: '/album/update',
    method: 'put',
    data
  })
}
export function remove(id) {
  return request({
    url: '/album/delete',
    method: 'delete',
    params:{
      id:id
    }
  })
}
