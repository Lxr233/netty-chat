package org.lxr.protocal.packet.response;

import java.util.List;
import lombok.Data;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;

/**
 * @description: 创建群聊响应数据包
 * @create: 2019-01-29 21:19
 **/
@Data
public class CreateGroupResponsePacket extends Packet
{

    private boolean success;
    private String groupId;
    private List<String> userNameList;

    @Override
    public Byte getCommand()
    {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
