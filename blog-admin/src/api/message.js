import request from "@/utils/request";

//留言管理
export function fetchMessage(params) {
  return request({
    url: '/message/list',
    method: 'get',
    params:params
  })
}
export function passBatch(data) {
  return request({
    url: '/message/passBatch',
    method: 'post',
    data
  })
}
export function deleteBatch(data) {
  return request({
    url: '/message/remove',
    method: 'delete',
    data
  })
}
// export function remove(id) {
//   return request({
//     url: '/message/remove',
//     method: 'delete',
//     params: {id:id}
//   })
// }
