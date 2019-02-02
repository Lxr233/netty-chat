package org.lxr.nettychatserver.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.lxr.protocal.packet.request.HeartBeatRequestPacket;
import org.lxr.protocal.packet.response.HeartBeatResponsePacket;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @create: 2019-02-02 11:46
 **/
@Sharable
@Component
public class HeartBeatRequestHandler  extends SimpleChannelInboundHandler<HeartBeatRequestPacket>
{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket requestPacket) {
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
