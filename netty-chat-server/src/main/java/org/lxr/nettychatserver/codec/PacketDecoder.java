package org.lxr.nettychatserver.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import org.lxr.codec.PacketCodec;
import org.springframework.stereotype.Component;

/**
 * @description: 解码器
 * @create: 2019-01-27 15:27
 **/
@Component
public class PacketDecoder extends ByteToMessageDecoder
{

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in,
            List<Object> out) throws Exception
    {
        out.add(PacketCodec.INSTANCE.decode(in));
    }
}
