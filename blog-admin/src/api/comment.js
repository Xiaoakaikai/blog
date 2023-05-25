import request from "@/utils/request";

//留言管理
export function fetchComment(params) {
  return request({
    url: '/comment/list',
    method: 'get',
    params:params
  })
}
export function deleteBatch(data) {
  return request({
    url: '/comment/remove',
    method: 'delete',
    data
  })
}

