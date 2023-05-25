import request from '@/utils/request'

export function fetchFeedback(params) {
  return request({
    url: '/feedback/list',
    method: 'get',
    params:params
  })
}
export function deleteBatchFeedback(data) {
  return request({
    url: '/feedback/deleteBatch',
    method: 'delete',
    data
  })
}

