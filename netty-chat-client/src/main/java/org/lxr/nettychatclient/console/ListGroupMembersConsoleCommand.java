package org.lxr.nettychatclient.console;

import io.netty.channel.Channel;
import java.util.Scanner;
import org.lxr.protocal.packet.request.ListGroupMembersRequestPacket;

/**
 * @description: 列出群成员
 * @create: 2019-01-30 20:32
 **/
public class ListGroupMembersConsoleCommand implements ConsoleCommand
{

    @Override
    public void exec(Scanner scanner, Channel channel)
    {
        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();

        System.out.print("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();

        listGroupMembersRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
