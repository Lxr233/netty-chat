package org.lxr.nettychatserver.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.lxr.protocal.packet.request.CreateGroupRequestPacket;
import org.lxr.protocal.packet.response.CreateGroupResponsePacket;
import org.lxr.util.IDUtil;
import org.lxr.util.SessionUtil;
import org.springframework.stereotype.Component;

/**
 * @description: 创建群聊
 * @create: 2019-01-29 20:38
 **/
@Component
@Sharable
@Slf4j
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket)
            throws Exception
    {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();

        List<String> userNameList = new ArrayList<>();

        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        for(String userId : userIdList){
            Channel channel = SessionUtil.getChannel(userId);
            if(channel!=null){
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        String groupId = IDUtil.randomId();
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setUserNameList(userNameList);

        //给每个客户端发送建群通知
        channelGroup.writeAndFlush(createGroupResponsePacket);

        log.info("群创建成功，id 为[ {} ] ",createGroupResponsePacket.getGroupId() );
        log.info("群里有：{}",createGroupResponsePacket.getUserNameList());

        // 保存群组相关的信息
        SessionUtil.bindChannelGroup(groupId, channelGroup);
    }
}
