package org.lxr.nettychatclient.client;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.lxr.nettychatclient.codec.PacketCodecHandler;
import org.lxr.nettychatclient.handler.CreateGroupResponseHandler;
import org.lxr.nettychatclient.handler.GroupMessageResponseHandler;
import org.lxr.nettychatclient.handler.HeartBeatTimerHandler;
import org.lxr.nettychatclient.handler.IMIdleStateHandler;
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

    @Autowired
    private GroupMessageResponseHandler groupMessageResponseHandler;

    @Autowired
    private PacketCodecHandler packetCodecHandler;

    @Autowired
    private HeartBeatTimerHandler heartBeatTimerHandler;

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception
    {
        ch.pipeline().addLast(new IMIdleStateHandler());
        ch.pipeline().addLast(new Spliter());
        ch.pipeline().addLast(packetCodecHandler);
        ch.pipeline().addLast(loginHandler);
        ch.pipeline().addLast(createGroupResponseHandler);
        ch.pipeline().addLast(logoutResponseHandler);
        ch.pipeline().addLast(joinGroupResponseHandler);
        ch.pipeline().addLast(quitGroupResponseHandler);
        ch.pipeline().addLast(listGroupMembersResponseHandler);
        ch.pipeline().addLast(groupMessageResponseHandler);
        ch.pipeline().addLast(heartBeatTimerHandler);
        ch.pipeline().addLast(messageResponseHandler);
    }
}
