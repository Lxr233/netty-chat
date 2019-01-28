package org.lxr.nettychatserver.handler;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import java.nio.charset.Charset;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.lxr.codec.PacketCodec;
import org.lxr.protocal.packet.Packet;
import org.lxr.protocal.packet.request.LoginRequestPacket;
import org.lxr.protocal.packet.response.LoginResponsePacket;
import org.lxr.util.LoginUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("loginHandler")
@Slf4j
@Sharable
public class LoginHandler extends SimpleChannelInboundHandler<LoginRequestPacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket)
            throws Exception
    {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());

        if (valid(loginRequestPacket))
        {
            log.info(new Date() +"   user:{} 登陆成功", loginRequestPacket.getUsername());
            loginResponsePacket.setSuccess(true);
            LoginUtil.markAsLogin(ctx.channel());
        }
        else
        {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
        }

        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
