package org.lxr.nettychatserver.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import java.util.ArrayList;
import java.util.List;
import org.lxr.protocal.packet.request.ListGroupMembersRequestPacket;
import org.lxr.protocal.packet.response.ListGroupMembersResponsePacket;
import org.lxr.session.Session;
import org.lxr.util.SessionUtil;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @create: 2019-01-31 10:58
 **/
@Component
@Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket listGroupMembersRequestPacket)
            throws Exception
    {
        String groupId = listGroupMembersRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        List<Session> sessionList = new ArrayList<>();
        for(Channel channel:channelGroup){
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);
        }

        ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
        listGroupMembersResponsePacket.setGroupId(groupId);
        listGroupMembersResponsePacket.setSessionList(sessionList);

        ctx.channel().writeAndFlush(listGroupMembersResponsePacket);
    }
}
