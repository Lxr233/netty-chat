package org.lxr.nettychatserver.handler;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.Charset;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.lxr.codec.PacketCodec;
import org.lxr.protocal.packet.Packet;
import org.lxr.protocal.packet.request.LoginRequestPacket;
import org.lxr.protocal.packet.response.LoginResponsePacket;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("serverHandler")
@Slf4j
@Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf requestByteBuf  = (ByteBuf) msg;

        Packet packet = PacketCodec.INSTANCE.decode(requestByteBuf);

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(packet.getVersion());

        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket)packet;
            if (valid(loginRequestPacket)) {
                log.info("user:{} 登陆成功",loginRequestPacket.getUsername());
                loginResponsePacket.setSuccess(true);
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
            }
        }

        ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(loginResponsePacket);
        ctx.channel().writeAndFlush(responseByteBuf);

    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
