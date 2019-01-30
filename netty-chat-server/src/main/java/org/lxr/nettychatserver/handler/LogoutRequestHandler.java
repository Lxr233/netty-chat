package org.lxr.nettychatserver.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.lxr.protocal.packet.request.LogoutRequestPacket;
import org.lxr.protocal.packet.response.LogoutResponsePacket;
import org.lxr.session.Session;
import org.lxr.util.SessionUtil;
import org.springframework.stereotype.Component;

/**
 * @description: 登出请求
 * @create: 2019-01-30 17:28
 **/
@Sharable
@Component
@Slf4j
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception
    {
        String userName = SessionUtil.getSession(ctx.channel()).getUserName();
        log.info("[{}]登出",userName);
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
