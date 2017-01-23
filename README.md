# springboot
## 简介
初次尝试使用spring boot构建项目,spring boot version:1.4.3,实践了一些spring相
关类与spring boot的整合.(如spring data jpa,mybatis,redis,spring security,
,spring cache,druid,swagger,spring websocket,quartz等)

## 说明
- springboot.sql,sqlboot_quartz.sql分别为测试数据库sql和quartz的集群sql
- 关于一些配置详情见/config下的一些configure类
- 通过swagger测试Controller,url为ip:port/admin/api
- 由于用到了spring security配置了/admin/*的url需要权限,用户名密码:admin
- 通过profile配置相关环境,具体见配置文件xml和/profile
