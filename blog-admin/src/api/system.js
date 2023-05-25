import request from '@/utils/request'

//菜单管理
export function fetchMenu() {
  return request({
    url: '/menu/getMenuTree',
    method: 'get',
    params: {}
  })
}
export function fetchApi(params) {
  return request({
    url: '/menu/getMenuApi',
    method: 'get',
    params: params
  })
}
export function createMenu(data) {
  return request({
    url: '/menu/create',
    method: 'post',
    data
  })
}
export function updateMenu(data) {
  return request({
    url: '/menu/update',
    method: 'put',
    data
  })
}
export function removeMenu(id) {
  return request({
    url: '/menu/remove',
    method: 'delete',
    params:{id:id}
  })
}
//角色管理
export function fetchRole(params) {
  return request({
    url: '/role/list',
    method: 'get',
    params:params
  })
}
export function queryRoleId(id) {
  return request({
    url: '/role/info',
    method: 'get',
    params: {
      roleId:id
    }
  })
}
export function updateRole(data) {
  return request({
    url: '/role/update',
    method: 'put',
    data
  })
}
export function createRole(data) {
  return request({
    url: '/role/create',
    method: 'post',
    data
  })
}
export function removeRole(data) {
  return request({
    url: '/role/remove',
    method: 'delete',
    data
  })
}

