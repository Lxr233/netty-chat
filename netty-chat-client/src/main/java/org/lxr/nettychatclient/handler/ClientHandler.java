package org.lxr.nettychatclient.handler;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.lxr.codec.PacketCodec;
import org.lxr.protocal.packet.Packet;
import org.lxr.protocal.packet.request.LoginRequestPacket;
import org.lxr.protocal.packet.response.LoginResponsePacket;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("clientHandler")
@Slf4j
@Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info(new Date() + ": 客户端开始登陆");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("lxr");
        loginRequestPacket.setPassword("pwd");


        ByteBuf buffer = PacketCodec.INSTANCE.encode(loginRequestPacket);

        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()) {
                log.info(new Date() + ": 客户端登录成功");
            } else {
                log.info(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        }
    }
}
