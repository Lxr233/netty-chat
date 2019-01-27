package org.lxr.nettychatclient.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.lxr.codec.PacketCodec;
import org.lxr.protocal.packet.Packet;
import org.springframework.stereotype.Component;

/**
 * @description: 编码器
 * @create: 2019-01-27 15:31
 **/
@Component
public class PacketEncoder extends MessageToByteEncoder<Packet>
{

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet,
            ByteBuf out) throws Exception
    {
        PacketCodec.INSTANCE.encode(out,packet);
    }
}
