import request from '@/utils/request'

export function fetchDataList(params) {
  return request({
    url: '/dictData/list',
    method: 'get',
    params:params
  })
}
export function addDictData(data) {
  return request({
    url: '/dictData/add',
    method: 'post',
    data
  })
}
export function updateDictData(data) {
  return request({
    url: '/dictData/update',
    method: 'post',
    data
  })
}
export function deleteDictData(id) {
  return request({
    url: '/dictData/delete',
    method: 'delete',
    params:{
      id:id
    }
  })
}
export function deleteBatchDictData(data) {
  return request({
    url: '/dictData/deleteBatch',
    method: 'delete',
    data
  })
}
export function getDataByDictType(data) {
  return request({
    url: '/dictData/getDataByDictType',
    method: 'post',
    data
  })
}
