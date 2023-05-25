import request from '@/utils/request'

export function fetchArticle(data) {
  return request({
    url: '/article/list',
    method: 'post',
    data
  })
}
export function info(id) {
  return request({
    url: '/article/info',
    method: 'get',
    params:{
      id:id
    }
  })
}
export function save(data) {
  return request({
    url: '/article/add',
    method: 'post',
    data
  })
}
export function update(data) {
  return request({
    url: '/article/update',
    method: 'put',
    data
  })
}
export function top(data) {
  return request({
    url: '/article/top',
    method: 'post',
    data
  })
}
export function pubOrShelf(data) {
  return request({
    url: '/article/pubOrShelf',
    method: 'post',
    data
  })
}
export function remove(id) {
  return request({
    url: '/article/' + id,
    method: 'delete',
  })
}
export function deleteBatch(data) {
  return request({
    url: '/article/deleteBatch',
    method: 'delete',
    data
  })
}
export function baiduSeo(data) {
  return request({
    url: '/article/baiduSeo',
    method: 'post',
    data
  })
}
export function reptile(url) {
  return request({
    url: '/article/reptile',
    method: 'get',
    params:{
      url:url
    }
  })
}
export function randomImg() {
  return request({
    url: '/article/randomImg',
    method: 'get',
    params:{}
  })
}
