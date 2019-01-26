package org.lxr.nettychatserver.server;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.lxr.nettychatserver.handler.ServerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyChannelInitializer extends ChannelInitializer<NioSocketChannel>
{
    @Autowired
    private ServerHandler serverHandler;

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception
    {
        ch.pipeline().addLast(serverHandler);
    }
}
