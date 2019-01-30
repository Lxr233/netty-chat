package org.lxr.nettychatclient.console;

import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * @description: 控制台执行抽象接口
 * @create: 2019-01-29 20:02
 **/
public interface ConsoleCommand
{
    void exec(Scanner scanner, Channel channel);
}
