package org.lxr.protocal.packet.request;

import java.util.List;
import lombok.Data;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description: 创建群聊请求数据包
 * @create: 2019-01-29 20:21
 **/
@Data
public class CreateGroupRequestPacket extends Packet
{

    private List<String> userIdList;

    @Override
    public Byte getCommand()
    {
        return Command.CREATE_GROUP_REQUEST;
    }
}
