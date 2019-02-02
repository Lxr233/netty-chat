package org.lxr.protocal.packet.response;

import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description:
 * @create: 2019-02-02 11:23
 **/
public class HeartBeatResponsePacket extends Packet
{

    @Override
    public Byte getCommand()
    {
        return Command.HEARTBEAT_RESPONSE;
    }
}
