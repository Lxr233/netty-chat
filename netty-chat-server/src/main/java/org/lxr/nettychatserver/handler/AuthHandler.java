package org.lxr.nettychatserver.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.lxr.util.LoginUtil;
import org.springframework.stereotype.Component;


/**
 * @description: 登录身份验证
 * @create: 2019-01-28 15:39
 **/
@Component
@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(!LoginUtil.hasLogin(ctx.channel())){
            ctx.channel().close();
        }
        else {
            //移除登录校验的handler
            ctx.pipeline().remove(this);
            super.channelRead(ctx,msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        if(LoginUtil.hasLogin(ctx.channel())){
            log.info("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        }
        else {
            log.info("无登录验证，强制关闭连接!");
        }
    }
}
