package org.lxr.nettychatserver.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;
import org.lxr.protocal.packet.request.MessageRequestPacket;
import org.lxr.protocal.packet.response.MessageResponsePacket;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @create: 2019-01-27 16:52
 **/
@Component
@Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
            MessageRequestPacket messageRequestPacket) throws Exception
    {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");

        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
