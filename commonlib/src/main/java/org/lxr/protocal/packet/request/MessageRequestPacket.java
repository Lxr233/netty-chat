package org.lxr.protocal.packet.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description: 消息请求数据包
 * @create: 2019-01-27 16:42
 **/
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet
{
    private String toUserId;
    private String message;

    public MessageRequestPacket(String toUserId,String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand()
    {
        return Command.MESSAGE_REQUEST;
    }
}
