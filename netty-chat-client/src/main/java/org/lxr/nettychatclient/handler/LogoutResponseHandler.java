package org.lxr.nettychatclient.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.lxr.protocal.packet.response.LogoutResponsePacket;
import org.lxr.session.Session;
import org.lxr.util.SessionUtil;
import org.springframework.stereotype.Component;

/**
 * @description: 登出响应
 * @create: 2019-01-30 18:58
 **/
@Component
@Sharable
@Slf4j
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket)
            throws Exception
    {
        String userName = SessionUtil.getSession(ctx.channel()).getUserName();
        System.out.println(userName + "登出");
        SessionUtil.unBindSession(ctx.channel());
    }
}
