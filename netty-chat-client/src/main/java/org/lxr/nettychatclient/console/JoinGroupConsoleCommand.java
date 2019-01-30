package org.lxr.nettychatclient.console;

import io.netty.channel.Channel;
import java.util.Scanner;
import org.lxr.protocal.packet.request.JoinGroupRequestPacket;

/**
 * @description: 加入群聊控制台
 * @author: l00427576
 * @create: 2019-01-30 19:41
 **/
public class JoinGroupConsoleCommand implements ConsoleCommand
{

    @Override
    public void exec(Scanner scanner, Channel channel)
    {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();
        System.out.print("输入 groupId，加入群聊：");
        String groupId = scanner.next();
        joinGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
