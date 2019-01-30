package org.lxr.protocal.packet.request;

import lombok.Data;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description: 退出群请求
 * @create: 2019-01-30 19:30
 **/
@Data
public class QuitGroupRequestPacket extends Packet
{

    private String groupId;

    @Override
    public Byte getCommand()
    {
        return Command.QUIT_GROUP_REQUEST;
    }
}
