package org.lxr.nettychatclient.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;
import org.lxr.protocal.packet.response.MessageResponsePacket;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: l00427576
 * @create: 2019-01-27 16:49
 **/
@Component
@Sharable
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
            MessageResponsePacket messageResponsePacket) throws Exception
    {
        System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
    }
}
