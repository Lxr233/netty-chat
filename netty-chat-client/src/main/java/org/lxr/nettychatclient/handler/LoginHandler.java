package org.lxr.nettychatclient.handler;


import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.lxr.protocal.packet.request.LoginRequestPacket;
import org.lxr.protocal.packet.response.LoginResponsePacket;
import org.lxr.util.LoginUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("loginHandler")
@Slf4j
@Sharable
public class LoginHandler extends SimpleChannelInboundHandler<LoginResponsePacket>
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info(new Date() + ": 客户端开始登陆");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("lxr");
        loginRequestPacket.setPassword("pwd");

        ctx.channel().writeAndFlush(loginRequestPacket);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
            LoginResponsePacket loginResponsePacket) throws Exception
    {

        if (loginResponsePacket.isSuccess())
        {
            log.info(new Date() + ": 客户端登录成功");
            LoginUtil.markAsLogin(ctx.channel());
        }
        else
        {
            log.info(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
        }

    }
}
