package org.lxr.nettychatserver.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.lxr.codec.PacketCodec;
import org.springframework.stereotype.Component;

/**
 * @description: 拆包器
 * 拆包器不能共享，也不能使用单例
 * 因为他内部实现是与每个 channel 有关，每个 Spliter 需要维持每个 channel 当前读到的数据，也就是说他是有状态的
 * @create: 2019-01-28 15:11
 **/
public class Spliter extends LengthFieldBasedFrameDecoder
{
    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 屏蔽非本协议的客户端
        if (in.getInt(in.readerIndex()) != PacketCodec.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
