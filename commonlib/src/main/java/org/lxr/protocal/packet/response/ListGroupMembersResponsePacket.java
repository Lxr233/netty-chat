package org.lxr.protocal.packet.response;

import java.util.List;
import lombok.Data;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;
import org.lxr.session.Session;

/**
 * @description: 列出群成员响应
 * @create: 2019-01-30 19:34
 **/
@Data
public class ListGroupMembersResponsePacket extends Packet
{
    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand()
    {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
