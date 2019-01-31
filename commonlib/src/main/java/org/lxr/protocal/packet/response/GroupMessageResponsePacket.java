package org.lxr.protocal.packet.response;

import lombok.Data;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;
import org.lxr.session.Session;

/**
 * @description: 发群聊消息的响应数据包
 * @create: 2019-01-31 11:32
 **/
@Data
public class GroupMessageResponsePacket extends Packet
{

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand()
    {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
