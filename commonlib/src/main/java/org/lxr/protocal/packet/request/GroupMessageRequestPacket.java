package org.lxr.protocal.packet.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description: 发群聊消息
 * @create: 2019-01-31 11:30
 **/
@Data
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet
{

    private String toGroupId;
    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getCommand()
    {
        return Command.GROUP_MESSAGE_REQUEST;
    }
}
