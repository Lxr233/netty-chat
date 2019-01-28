package org.lxr.nettychatclient.client;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.lxr.nettychatclient.codec.PacketDecoder;
import org.lxr.nettychatclient.codec.PacketEncoder;
import org.lxr.nettychatclient.handler.LoginHandler;
import org.lxr.nettychatclient.handler.MessageResponseHandler;
import org.lxr.nettychatclient.handler.Spliter;
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

    @Autowired
    private MessageResponseHandler messageResponseHandler;

    @Autowired
    private Spliter spliter;

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception
    {
        ch.pipeline().addLast(spliter);
        ch.pipeline().addLast(packetDecoder);
        ch.pipeline().addLast(packetEncoder);
        ch.pipeline().addLast(loginHandler);
        ch.pipeline().addLast(messageResponseHandler);
    }
}
