package org.lxr.nettychatserver.server;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.lxr.nettychatserver.codec.PacketCodecHandler;
import org.lxr.nettychatserver.handler.AuthHandler;
import org.lxr.nettychatserver.handler.CreateGroupRequestHandler;
import org.lxr.nettychatserver.handler.GroupMessageRequestHandler;
import org.lxr.nettychatserver.handler.IMHandler;
import org.lxr.nettychatserver.handler.JoinGroupRequestHandler;
import org.lxr.nettychatserver.handler.ListGroupMembersRequestHandler;
import org.lxr.nettychatserver.handler.LoginRequestHandler;
import org.lxr.nettychatserver.handler.LogoutRequestHandler;
import org.lxr.nettychatserver.handler.MessageRequestHandler;
import org.lxr.nettychatserver.handler.QuitGroupRequestHandler;
import org.lxr.nettychatserver.handler.Spliter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyChannelInitializer extends ChannelInitializer<NioSocketChannel>
{
    @Autowired
    private LoginRequestHandler loginRequestHandler;

    @Autowired
    private IMHandler imHandler;

    @Autowired
    private AuthHandler authHandler;

    @Autowired
    private PacketCodecHandler packetCodecHandler;

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception
    {
        ch.pipeline().addLast(new Spliter());
        ch.pipeline().addLast(packetCodecHandler);
        ch.pipeline().addLast(loginRequestHandler);
        ch.pipeline().addLast(authHandler);
        ch.pipeline().addLast(imHandler);
    }
}
