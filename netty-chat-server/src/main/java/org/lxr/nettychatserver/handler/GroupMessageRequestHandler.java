package org.lxr.nettychatserver.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.lxr.protocal.packet.request.GroupMessageRequestPacket;
import org.lxr.protocal.packet.response.GroupMessageResponsePacket;
import org.lxr.session.Session;
import org.lxr.util.SessionUtil;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @create: 2019-01-31 11:39
 **/
@Sharable
@Component
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception
    {
        String groupId = groupMessageRequestPacket.getToGroupId();
        String message = groupMessageRequestPacket.getMessage();
        Session fromUser = SessionUtil.getSession(ctx.channel());

        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setFromGroupId(groupId);
        groupMessageResponsePacket.setMessage(message);
        groupMessageResponsePacket.setFromUser(fromUser);

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.writeAndFlush(groupMessageResponsePacket);
    }
}
