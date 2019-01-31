package org.lxr.nettychatclient.console;

import io.netty.channel.Channel;
import java.util.Scanner;
import org.lxr.protocal.packet.request.GroupMessageRequestPacket;

/**
 * @description: 发群聊消息
 * @create: 2019-01-31 11:35
 **/
public class SendToGroupConsoleCommand implements ConsoleCommand
{

    @Override
    public void exec(Scanner scanner, Channel channel)
    {
        System.out.print("要发消息给的群组id：");
        String toGroupId = scanner.next();
        System.out.print("消息内容：");
        String message = scanner.next();
        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));
    }
}
