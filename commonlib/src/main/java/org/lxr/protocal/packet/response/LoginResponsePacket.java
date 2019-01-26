package org.lxr.protocal.packet.response;

import lombok.Data;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description: 登录响应数据包
 * @create: 2019-01-26 16:27
 **/
@Data
public class LoginResponsePacket extends Packet
{

    private boolean success;
    private String reason;

    @Override
    public Byte getCommand()
    {
        return Command.LOGIN_RESPONSE;
    }
}
