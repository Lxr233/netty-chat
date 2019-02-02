package org.lxr.nettychatserver.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.lxr.session.Session;
import org.lxr.util.SessionUtil;
import org.springframework.stereotype.Component;

/**
 * @description: 空闲检测
 * @create: 2019-02-02 09:48
 **/
@Slf4j
public class IMIdleStateHandler extends IdleStateHandler
{
    private static final int READER_IDLE_TIME = 15;

    public IMIdleStateHandler(){
        super(READER_IDLE_TIME,0,0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        String userName = SessionUtil.getSession(ctx.channel()).getUserName();
        log.info("[{}] {}秒内未读到数据，关闭连接",userName,READER_IDLE_TIME);
        ctx.channel().close();
    }
}
