package org.lxr.protocal.packet.response;

import lombok.Data;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description: 登出响应数据包
 * @create: 2019-01-30 17:14
 **/
@Data
public class LogoutResponsePacket extends Packet
{
    private boolean success;
    @Override
    public Byte getCommand()
    {
        return Command.LOGOUT_RESPONSE;
    }
}
