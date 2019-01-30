package org.lxr.protocal.packet.request;

import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description: 登出请求数据包
 * @create: 2019-01-30 17:13
 **/
public class LogoutRequestPacket extends Packet
{

    @Override
    public Byte getCommand()
    {
        return Command.LOGOUT_REQUEST;
    }
}
