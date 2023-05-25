import request from '@/utils/request'

export function getSystemConfig() {
  return request({
    url: '/config/getConfig',
    method: 'get',
    params: {}
  })
}
export function updateSystemConfig(data) {
  return request({
    url: '/config/update',
    method: 'put',
    data
  })
}
