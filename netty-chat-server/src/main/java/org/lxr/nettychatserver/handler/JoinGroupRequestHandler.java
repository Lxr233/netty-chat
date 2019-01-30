package org.lxr.nettychatserver.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;
import org.lxr.protocal.packet.request.JoinGroupRequestPacket;
import org.lxr.protocal.packet.response.JoinGroupResponsePacket;
import org.lxr.util.SessionUtil;
import org.springframework.stereotype.Component;

/**
 * @description: 加入群聊请求
 * @create: 2019-01-30 19:44
 **/
@Sharable
@Slf4j
@Component
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception
    {
        String groupId = joinGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setSuccess(true);
        joinGroupResponsePacket.setGroupId(groupId);
        ctx.channel().writeAndFlush(joinGroupResponsePacket);
    }
}
