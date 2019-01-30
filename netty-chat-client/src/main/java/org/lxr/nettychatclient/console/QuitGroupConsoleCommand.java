package org.lxr.nettychatclient.console;

import io.netty.channel.Channel;
import java.util.Scanner;
import org.lxr.protocal.packet.request.QuitGroupRequestPacket;

/**
 * @description: 退出群聊
 * @create: 2019-01-30 20:23
 **/
public class QuitGroupConsoleCommand implements ConsoleCommand
{

    @Override
    public void exec(Scanner scanner, Channel channel)
    {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();

        System.out.print("输入 groupId，退出群聊：");
        String groupId = scanner.next();

        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
