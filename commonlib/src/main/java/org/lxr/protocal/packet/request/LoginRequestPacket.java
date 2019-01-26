package org.lxr.protocal.packet.request;

import lombok.Data;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description: 登录请求数据包
 * @create: 2019-01-22 22:13
 **/
@Data
public class LoginRequestPacket extends Packet
{
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand()
    {
        return Command.LOGIN_REQUEST;
    }
}
