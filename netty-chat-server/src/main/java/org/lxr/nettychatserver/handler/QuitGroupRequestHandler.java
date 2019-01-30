package org.lxr.nettychatserver.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.lxr.protocal.packet.request.QuitGroupRequestPacket;
import org.lxr.protocal.packet.response.QuitGroupResponsePacket;
import org.lxr.util.SessionUtil;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @create: 2019-01-30 20:25
 **/
@Sharable
@Component
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception
    {
        String groupId = quitGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        quitGroupResponsePacket.setGroupId(groupId);
        quitGroupResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(quitGroupResponsePacket);
    }
}
