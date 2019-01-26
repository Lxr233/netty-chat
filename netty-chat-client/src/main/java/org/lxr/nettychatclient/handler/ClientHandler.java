package org.lxr.nettychatclient.handler;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.lxr.protocal.packet.request.LoginRequestPacket;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("clientHandler")
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info(new Date() + ": 客户端开始登陆");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("lxr");
        loginRequestPacket.setPassword("pwd");


        ByteBuf buffer = Unpooled.copiedBuffer("hello",Charset.forName("utf-8"));

        ctx.channel().writeAndFlush(buffer);
    }
}
