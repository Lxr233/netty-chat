package org.lxr.nettychatclient.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.lxr.protocal.packet.response.GroupMessageResponsePacket;
import org.lxr.session.Session;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @create: 2019-01-31 19:18
 **/
@Sharable
@Component
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket responsePacket)
            throws Exception
    {
        String fromGroupId = responsePacket.getFromGroupId();
        Session fromUser = responsePacket.getFromUser();
        System.out.println("收到群[" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + responsePacket.getMessage());
    }
}
