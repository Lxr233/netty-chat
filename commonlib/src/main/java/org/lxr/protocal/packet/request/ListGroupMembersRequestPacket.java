package org.lxr.protocal.packet.request;

import lombok.Data;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description: 列出群成员请求
 * @author: l00427576
 * @create: 2019-01-30 19:29
 **/
@Data
public class ListGroupMembersRequestPacket extends Packet
{
    private String groupId;

    @Override
    public Byte getCommand()
    {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
