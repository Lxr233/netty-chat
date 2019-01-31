package org.lxr.nettychatclient.client;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.lxr.nettychatclient.codec.PacketDecoder;
import org.lxr.nettychatclient.codec.PacketEncoder;
import org.lxr.nettychatclient.handler.CreateGroupResponseHandler;
import org.lxr.nettychatclient.handler.GroupMessageResponseHandler;
import org.lxr.nettychatclient.handler.JoinGroupResponseHandler;
import org.lxr.nettychatclient.handler.ListGroupMembersResponseHandler;
import org.lxr.nettychatclient.handler.LoginHandler;
import org.lxr.nettychatclient.handler.LogoutResponseHandler;
import org.lxr.nettychatclient.handler.MessageResponseHandler;
import org.lxr.nettychatclient.handler.QuitGroupResponseHandler;
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
    private CreateGroupResponseHandler createGroupResponseHandler;

    @Autowired
    private LogoutResponseHandler logoutResponseHandler;

    @Autowired
    private JoinGroupResponseHandler joinGroupResponseHandler;

    @Autowired
    private QuitGroupResponseHandler quitGroupResponseHandler;

    @Autowired
    private ListGroupMembersResponseHandler listGroupMembersResponseHandler;

    @Autowired GroupMessageResponseHandler groupMessageResponseHandler;

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception
    {
        ch.pipeline().addLast(new Spliter());
        ch.pipeline().addLast(packetDecoder);
        ch.pipeline().addLast(packetEncoder);
        ch.pipeline().addLast(loginHandler);
        ch.pipeline().addLast(createGroupResponseHandler);
        ch.pipeline().addLast(logoutResponseHandler);
        ch.pipeline().addLast(joinGroupResponseHandler);
        ch.pipeline().addLast(quitGroupResponseHandler);
        ch.pipeline().addLast(listGroupMembersResponseHandler);
        ch.pipeline().addLast(groupMessageResponseHandler);
        ch.pipeline().addLast(messageResponseHandler);
    }
}
