package org.lxr.nettychatclient.client;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.lxr.nettychatclient.handler.ClientHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyChannelInitializer extends ChannelInitializer<NioSocketChannel>
{
    @Autowired
    private ClientHandler clientHandler;

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception
    {
        ch.pipeline().addLast(clientHandler);
    }
}
