package com.bkk.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 系统配置表(SystemConfig)表实体类
 *
 * @author makejava
 * @since 2023-05-22 15:11:35
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_system_config")
public class SystemConfig  {
    //主键@TableId
    private Long id;

    //七牛云公钥
    private String qiNiuAccessKey;
    //七牛云私钥
    private String qiNiuSecretKey;
    //七牛云存储区域 华东（z0），华北(z1)，华南(z2)，北美(na0)，东南亚(as0)
    private String qiNiuArea;
    //七牛云上传空间
    private String qiNiuBucket;
    //七牛云域名前缀：http://images.moguit.cn
    private String qiNiuPictureBaseUrl;
    //文件上传七牛云(0:否， 1:是)
    private String uploadQiNiu;
    //是否开启注册用户邮件激活(0:否， 1:是)
    private String openEmailActivate;
    //是否开启邮件通知(0:否， 1:是)
    private String startEmailNotification;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //是否开启仪表盘通知(0:否， 1:是)
    private String openDashboardNotification;
    //仪表盘通知【用于首次登录弹框】MD
    private String dashboardNotificationMd;
    //仪表盘通知【用于首次登录弹框】
    private String dashboardNotification;
    //搜索模式【0:SQL搜索 、1：全文检索】
    private Integer searchModel;
    //邮箱地址
    private String emailHost;
    //邮箱发件人
    private String emailUsername;
    //邮箱授权码
    private String emailPassword;
    //邮箱发送端口
    private Integer emailPort;
    //启用邮箱发送
    private Integer openEmail;
    //本地文件地址
    private String localFileUrl;
    //文件上传方式 1:本地 2：七牛云
    private Integer fileUploadWay;
    //阿里云ak
    private String aliYunAccessKey;
    //阿里云sk
    private String aliYunSecretKey;
    //阿里云存储桶名
    private String aliYunBucket;
    //阿里云Endpoint
    private String aliYunEndpoint;



}
