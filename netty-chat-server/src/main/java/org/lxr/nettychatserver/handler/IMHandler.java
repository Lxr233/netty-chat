package org.lxr.nettychatserver.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 合并平行handler
 * @create: 2019-02-01 19:43
 **/
@Sharable
@Component
public class IMHandler extends SimpleChannelInboundHandler<Packet>
{
    private Map<Byte,SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    @Autowired
    private CreateGroupRequestHandler createGroupRequestHandler;

    @Autowired
    private LogoutRequestHandler logoutRequestHandler;

    @Autowired
    private JoinGroupRequestHandler joinGroupRequestHandler;

    @Autowired
    private QuitGroupRequestHandler quitGroupRequestHandler;

    @Autowired
    private ListGroupMembersRequestHandler listGroupMembersRequestHandler;

    @Autowired
    private GroupMessageRequestHandler groupMessageRequestHandler;

    @Autowired
    private MessageRequestHandler messageRequestHandler;

    @PostConstruct
    private void init(){
        handlerMap.put(Command.MESSAGE_REQUEST, messageRequestHandler);
        handlerMap.put(Command.CREATE_GROUP_REQUEST, createGroupRequestHandler);
        handlerMap.put(Command.JOIN_GROUP_REQUEST, joinGroupRequestHandler);
        handlerMap.put(Command.QUIT_GROUP_REQUEST, quitGroupRequestHandler);
        handlerMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, listGroupMembersRequestHandler);
        handlerMap.put(Command.GROUP_MESSAGE_REQUEST, groupMessageRequestHandler);
        handlerMap.put(Command.LOGOUT_REQUEST, logoutRequestHandler);
    }

    public IMHandler(){
        handlerMap = new HashMap<>();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception
    {
        handlerMap.get(packet.getCommand()).channelRead(ctx,packet);
    }
}
