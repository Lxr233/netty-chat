package org.lxr.nettychatclient.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.lxr.protocal.packet.response.CreateGroupResponsePacket;
import org.springframework.stereotype.Component;

/**
 * @description: 创建群聊响应
 * @create: 2019-01-30 16:33
 **/
@Component
@Sharable
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket)
            throws Exception
    {
        System.out.print("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + createGroupResponsePacket.getUserNameList());
    }
}
