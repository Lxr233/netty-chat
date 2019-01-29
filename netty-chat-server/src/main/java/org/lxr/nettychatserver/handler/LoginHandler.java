package org.lxr.nettychatserver.handler;


import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.lxr.protocal.packet.request.LoginRequestPacket;
import org.lxr.protocal.packet.response.LoginResponsePacket;
import org.lxr.session.Session;
import org.lxr.util.SessionUtil;
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
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());

        if (valid(loginRequestPacket))
        {
            loginResponsePacket.setSuccess(true);
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);

            log.info(new Date() +"   用户:{} 登陆成功", loginRequestPacket.getUserName());

            Session session = new Session(userId,loginRequestPacket.getUserName());
            SessionUtil.bindSession(session,ctx.channel());
        }
        else
        {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            log.info(new Date() +"   用户:{} 登陆失败");
        }

        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        String userName = SessionUtil.getSession(ctx.channel()).getUserName();
        log.info("用户:{} 退出",userName);
        SessionUtil.unBindSession(ctx.channel());
    }
}
