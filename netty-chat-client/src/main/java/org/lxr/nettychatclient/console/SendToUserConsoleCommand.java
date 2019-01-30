package org.lxr.nettychatclient.console;

import io.netty.channel.Channel;
import java.util.Scanner;
import org.lxr.protocal.packet.request.MessageRequestPacket;

/**
 * @description: 单聊控制台实现类
 * @author: l00427576
 * @create: 2019-01-29 20:10
 **/
public class SendToUserConsoleCommand implements ConsoleCommand
{

    @Override
    public void exec(Scanner scanner, Channel channel)
    {
        System.out.print("输入消息接收用户：");
        String toUserId = scanner.next();
        System.out.print("输入发送消息内容：");
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}
