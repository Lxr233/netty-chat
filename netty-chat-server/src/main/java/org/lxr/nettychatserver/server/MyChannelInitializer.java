package org.lxr.nettychatserver.server;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.lxr.nettychatserver.codec.PacketDecoder;
import org.lxr.nettychatserver.codec.PacketEncoder;
import org.lxr.nettychatserver.handler.AuthHandler;
import org.lxr.nettychatserver.handler.CreateGroupRequestHandler;
import org.lxr.nettychatserver.handler.LoginRequestHandler;
import org.lxr.nettychatserver.handler.LogoutRequestHandler;
import org.lxr.nettychatserver.handler.MessageRequestHandler;
import org.lxr.nettychatserver.handler.Spliter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyChannelInitializer extends ChannelInitializer<NioSocketChannel>
{
    @Autowired
    private LoginRequestHandler loginRequestHandler;

    @Autowired
    private MessageRequestHandler messageRequestHandler;

    @Autowired
    private AuthHandler authHandler;

    @Autowired
    private CreateGroupRequestHandler createGroupRequestHandler;

    @Autowired
    private LogoutRequestHandler logoutRequestHandler;

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception
    {
        ch.pipeline().addLast(new Spliter());
        ch.pipeline().addLast(new PacketDecoder());
        ch.pipeline().addLast(new PacketEncoder());
        ch.pipeline().addLast(loginRequestHandler);
        ch.pipeline().addLast(authHandler);
        ch.pipeline().addLast(createGroupRequestHandler);
        ch.pipeline().addLast(logoutRequestHandler);
        ch.pipeline().addLast(messageRequestHandler);
    }
}
