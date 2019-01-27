package org.lxr.protocal.packet.response;

import lombok.Data;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description: 消息响应数据包
 * @create: 2019-01-27 16:45
 **/
@Data
public class MessageResponsePacket extends Packet
{
    private String message;

    @Override
    public Byte getCommand() {

        return Command.MESSAGE_RESPONSE;
    }
}
