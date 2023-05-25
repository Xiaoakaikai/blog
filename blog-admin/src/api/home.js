import request from '@/utils/request'

export function init() {
  return request({
    url: '/home/init',
    method: 'get',
    params:{}
  })
}
export function lineCount() {
  return request({
    url: '/home/lineCount',
    method: 'get',
    params:{}
  })
}
export function systemInfo() {
  return request({
    url: '/home/systemInfo',
    method: 'get',
    params:{}
  })
}
export function cacheInfo() {
  return request({
    url: '/home/cache',
    method: 'get',
    params:{}
  })
}
export function report() {
  return request({
    url: '/home/report',
    method: 'get',
    params:{}
  })
}
