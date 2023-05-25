## 博客介绍

<p align="center">
  <a href="https://www.xiaoakai.top">
    <img src="https://www.xiaoakai.top/51cdbf71f3234e46bad24365d04a74c1.png" width=150 alt="小阿凯的博客" style="border-radius: 50%">
  </a>
</p>

<center>

# 小阿凯的博客
**基于Springboot + Vue开发的前后端分离博客项目**

</center>




<p align="center">
   <a target="_blank" href="https://github.com/Xiaoakaikai/blog">
      <img src="https://img.shields.io/hexpm/l/plug.svg"/>
      <img src="https://img.shields.io/badge/JDK-1.8+-green.svg"/>
      <img src="https://img.shields.io/badge/springboot-2.5.0-green"/>
      <img src="https://img.shields.io/badge/vue-2.5.17-green"/>
      <img src="https://img.shields.io/badge/mysql-8.0.28-green"/>
      <img src="https://img.shields.io/badge/mybatis--plus-3.4.3-green"/>
      <img src="https://img.shields.io/badge/redis-7.0.9-green"/>
   </a>
</p>

# 平台简介
* 前后台界面采用[拾壹博客](https://gitee.com/quequnlong/shiyi-blog)，页面美观
* 后台管理基于SpringBoot开发，含有侧边栏，历史标签等
* 前后端分离，使用腾讯云轻量级服务器部署
* 支持动态权限修改、动态菜单
* 说说、友链、相册、留言弹幕墙、音乐播放器
* 支持代码高亮、图片预览、黑夜模式、点赞、取消点赞等功能
* 发布评论、回复评论、表情包
* 文章搜索支持关键字高亮分词
* 文章编辑使用 Markdown 编辑器
* 含有最新评论、文章目录、文章推荐和文章置顶功能
* Aop注解实现日志管理
* 代码支持多种搜索模式（Elasticsearch 或 MYSQL），支持多种文件上传模式（OSS、COS、本地）
* 采用 Restful 风格的 API，注释完善，有利于开发者学习

# 技术应用

**前端**：Vue

**后端**：Spring Boot + Mysql + Redis + Spring Security + Mybatis-Plus + Nginx

# 开发环境

依赖|版本
:-:|:-:
JDK|1.8
SpringBoot|2.5.0
Mybatis-Plus|3.4.3
Mysql|8.0.31
Redis|7.0.9

开发工具|说明
:-:|:-:
IDEA|Java 开发工具 IDE
VSCode|Vue 开发工具 IDE
Navicat|MySQL 远程连接工具
Redis Desktop Manager|Redis 远程连接工具
Xshell|Linux 远程连接工具
Xftp|Linux 文件上传工具

# 运行环境
服务器： 腾讯云2核4G CentOS7.6

对象存储： 本地/七牛云OSS

最低配置： 1核2G服务器（关闭ElasticSearch）

# 在线体验

### **博客链接**：[www.xiaoakai.top](https://www.xiaoakai.top/)

### **后台链接**：[admin.xiaoakai.top](https://admin.xiaoakai.top/)
**账号**：test ，**密码**：123456

### **gitee地址**：[https://gitee.com/xiao_a_kai/blog](https://gitee.com/xiao_a_kai/blog)
### 前台接口文档：[https://www.xiaoakai.top/xak/doc.html](https://www.xiaoakai.top/xak/doc.html)
### 后台接口文档：[https://admin.xiaoakai.top/xak/doc.html](https://admin.xiaoakai.top/xak/doc.html)

# 项目截图
![博客.png](https://www.xiaoakai.top/ec214c39a27c4ce59c9ee047cb306711.png)
![博客后台.png](https://www.xiaoakai.top/039e34e492404187ba0e204aa1e965e8.png)

# 项目总结

这个项目总的来说，自己做起来还算顺利。本人做的时候不太会前端，因此改前端一些bug时非常吃力，后端还算ok，主要还是借鉴大佬们的。整个项目做下来发现自己的漏洞还很多，需要掌握的技术还有很多很多，学无止境。

以下是我参考的一些大佬，非常感谢。
* ### [三更草堂](https://www.bilibili.com/video/BV1hq4y1F7zk/?spm_id_from=333.1007.top_right_bar_window_custom_collection.content.click&vd_source=927c70bec54714105e8a68e14960109f)
* ### [拾壹](https://gitee.com/quequnlong/shiyi-blog)
* ### [掐指yi算’逢考必过](https://gitee.com/wu_shengdong/blog)