package org.lxr.nettychatserver.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.lxr.protocal.packet.request.MessageRequestPacket;
import org.lxr.protocal.packet.response.MessageResponsePacket;
import org.lxr.session.Session;
import org.lxr.util.SessionUtil;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @create: 2019-01-27 16:52
 **/
@Component
@Sharable
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        //拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        //通过消息发送方的会话消息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        //获取消息接收方的channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        //发消息给接收方
        if(toUserChannel!=null && SessionUtil.hasLogin(toUserChannel)){
            toUserChannel.writeAndFlush(messageResponsePacket);
        }
        else{
            log.error("[{}]不在线，发送失败",messageRequestPacket.getToUserId());
        }
    }
}
