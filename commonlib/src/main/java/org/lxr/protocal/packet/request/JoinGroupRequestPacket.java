package org.lxr.protocal.packet.request;

import lombok.Data;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description: 加入群聊请求数据包
 * @create: 2019-01-30 19:26
 **/
@Data
public class JoinGroupRequestPacket extends Packet
{
    private String groupId;
    @Override
    public Byte getCommand()
    {
        return Command.JOIN_GROUP_REQUEST;
    }
}
