package org.lxr.protocal.packet.response;

import lombok.Data;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description: 加入群聊响应
 * @create: 2019-01-30 19:33
 **/
@Data
public class JoinGroupResponsePacket extends Packet
{
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand()
    {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
