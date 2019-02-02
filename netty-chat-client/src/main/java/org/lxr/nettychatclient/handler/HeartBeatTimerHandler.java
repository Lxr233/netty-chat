package org.lxr.nettychatclient.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.concurrent.TimeUnit;
import org.lxr.protocal.packet.request.HeartBeatRequestPacket;
import org.springframework.stereotype.Component;

/**
 * @description: 定时发送心跳
 * @author: l00427576
 * @create: 2019-02-02 11:25
 **/
@Component
@Sharable
public class HeartBeatTimerHandler  extends ChannelInboundHandlerAdapter
{
    //这个时间段通常要比服务端的空闲检测时间的一半要短一些
    private static final int HEARTBEAT_INTERVAL = 7;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);

        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        //schedule()，类似 jdk 的延时任务机制，可以隔一段时间之后执行一个任务
        ctx.executor().schedule(() -> {

            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }

        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }

}
