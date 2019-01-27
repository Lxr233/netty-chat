package org.lxr.nettychatserver.server;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.lxr.nettychatserver.codec.PacketDecoder;
import org.lxr.nettychatserver.codec.PacketEncoder;
import org.lxr.nettychatserver.handler.LoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyChannelInitializer extends ChannelInitializer<NioSocketChannel>
{
    @Autowired
    private LoginHandler loginHandler;

    @Autowired
    private PacketDecoder packetDecoder;

    @Autowired
    private PacketEncoder packetEncoder;

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception
    {
        ch.pipeline().addLast(packetDecoder);
        ch.pipeline().addLast(packetEncoder);
        ch.pipeline().addLast(loginHandler);
    }
}
