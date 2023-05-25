import request from '@/utils/request'

export function fetchPages() {
  return request({
    url: '/page/list',
    method: 'get',
    params:{}
  })
}export function addPage(data) {
  return request({
    url: '/page/add',
    method: 'post',
    data
  })
}export function updatePage(data) {
  return request({
    url: '/page/update',
    method: 'put',
    data
  })
}export function removePage(id) {
  return request({
    url: '/page/delete',
    method: 'delete',
    params:{
      id:id
    }
  })
}
