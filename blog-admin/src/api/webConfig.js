import request from '@/utils/request'

export function getWebConfig() {
  return request({
    url: '/webConfig/list',
    method: 'get',
    params: {}
  })
}
export function update(data) {
  return request({
    url: '/webConfig/update',
    method: 'put',
    data
  })
}
