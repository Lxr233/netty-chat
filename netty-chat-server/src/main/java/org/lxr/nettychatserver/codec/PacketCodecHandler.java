package org.lxr.nettychatserver.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import java.util.List;
import org.lxr.codec.PacketCodec;
import org.lxr.protocal.packet.Packet;
import org.springframework.stereotype.Component;

/**
 * @description: 合并之后的编解码handler
 * @author: l00427576
 * @create: 2019-02-01 14:43
 **/
@Sharable
@Component
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet>
{

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> out) throws Exception
    {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodec.INSTANCE.encode(byteBuf,packet);
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception
    {
        out.add(PacketCodec.INSTANCE.decode(byteBuf));
    }
}
