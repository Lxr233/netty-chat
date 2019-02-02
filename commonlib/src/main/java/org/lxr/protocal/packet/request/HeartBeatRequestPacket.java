package org.lxr.protocal.packet.request;

import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description:
 * @create: 2019-02-02 11:22
 **/
public class HeartBeatRequestPacket extends Packet
{

    @Override
    public Byte getCommand()
    {
        return Command.HEARTBEAT_REQUEST;
    }
}
