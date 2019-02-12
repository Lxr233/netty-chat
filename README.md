# netty-chat
基于netty的聊天系统
客户端和服务端分别使用springboot编写，并共同依赖commonlib。
commonlib是普通maven程序，编译后的jar包被客户端和服务端在pom文件中引用。

该程序主要功能：
- 实现客户端点对点单聊
- 实现客户端群聊
- 群聊的发起与退出
- 自定义传输协议
- 空闲与心跳检测
- 解决拆包粘包
- 使用channelHandler的热插拔实现客户端身份校验
