import request from '@/utils/request'

export function fetchList(params) {
    return request({
        url: '/article/list',
        method: 'get',
        params: params
    })
}
export function getArticleInfo(id) {
    return request({
        url: '/article/' + id,
        method: 'get',
    })
}
export function searchArticle(keywords) {
    return request({
        url: '/article/searchArticle',
        method: 'get',
        params: {
            keywords:keywords
        }
    })
}
//分类标签id获取文章
export function queryIdToArticles(params) {
    return request({
        url: '/article/list',
        method: 'get',
        params: params
    })
}
export function like(id) {
    return request({
        url: '/article/articleLike',
        method: 'get',
        params: {articleId:id}
    })
}
export function checkSecret(params) {
    return request({
        url: '/article/checkSecret',
        method: 'get',
        params: params
    })
}
//友链页面请求接口
export function addLink(data) {
    return request({
        url: '/friend/add',
        method: 'POST',
        data
    })
}
export function fetchFriend() {
    return request({
        url: '/friend/list',
        method: 'get',
        params: {}
    })
}
export function getWebSiteInfo() {
    return request({
        url: '/home/webSiteInfo',
        method: 'get',
        params: {}
    })
}

//留言接口
export function addMessage(data) {
    return request({
        url: '/message/add',
        method: 'post',
        data
    })
}

export function listMessage() {
    return request({
        url: '/message/webMessage',
        method: 'get',
        params: {}
    })
}
//添加评论
export function addComment(data) {
    return request({
        url: '/comment/addComment',
        method: 'post',
        data
    })
}
//评论列表
export function fetchComments(params) {
    return request({
          url: '/comment/comments',
          method: 'get',
          params: params
      })
}
//查询回复评论
export function repliesByComId(params) {
    return request({
          url: '/comment/repliesByComId',
          method: 'get',
          params: params
      })
}

//标签接口
export function getTags() {
    return request({
        url: '/tags/list',
        method: 'get',
        params: {}
    })
}
//分类接口
export function getCategory() {
    return request({
        url: '/category/categoryList',
        method: 'get',
        params: {}
    })
}
//归档接口
export function getArchive(params) {
    return request({
        url: '/article/archive',
        method: 'get',
        params: params
    })
}
//qq登录
export function qqLogin(data) {
    return request({
        url: '/user/login',
        method: 'post',
        data
    })
}
//qq退出
export function logout() {
    return request({
        url: '/logout',
        method: 'post',
    })
}
//gitee登录
export function gitEELogin(code) {
    return request({
        url: '/user/gitEELogin',
        method: 'get',
        params: {
            code:code
        }
    })
}
//微博登录
export function weiboLogin(code) {
    return request({
        url: '/user/weiboLogin',
        method: 'get',
        params: {
            code:code
        }
    })
}
//添加访客信息
export function report() {
    return request({
        url: '/home/report',
        method: 'get',
        params: {}
    })
}
//相册接口
export function getAlbum() {
    return request({
        url: '/album/list',
        method: 'get',
        params: {}
    })
}
export function getPhotos(params) {
    return request({
        url: '/album/listPhotos',
        method: 'get',
        params: params
    })
}
export function addFeedback(data) {
    return request({
        url: '/feedback/add',
        method: 'post',
        data
    })
}
export function sendEmailCode(email) {
    return request({
        url: '/user/sendEmailCode',
        method: 'get',
        params:{
            email:email
        }
    })
}
export function bindEmail(data) {
    return request({
        url: '/user/bindEmail',
        method: 'post',
        data
    })
}
export function emailRegister(data) {
    return request({
        url: '/user/emailRegister',
        method: 'post',
        data
    })
}
export function emailLogin(data) {
    return request({
        url: '/login',
        method: 'post',
        data
    })
}
export function updatePassword(data) {
    return request({
        url: '/user/updatePassword',
        method: 'post',
        data
    })
}
export function upload(data) {
    return request({
        url: '/file/upload',
        method: 'POST',
        headers:{'Content-Type': 'multipart/articles-data'},
        data
    })
}
export function updateUser(data) {
    return request({
        url: '/user/updateUser',
        method: 'put',
        data
    })
}

