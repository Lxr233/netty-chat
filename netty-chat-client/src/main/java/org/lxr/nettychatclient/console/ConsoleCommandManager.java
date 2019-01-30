package org.lxr.nettychatclient.console;

import io.netty.channel.Channel;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @description: 控制台操作管理类
 * @create: 2019-01-29 20:06
 **/
public class ConsoleCommandManager implements ConsoleCommand
{

    private Map<String,ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager(){
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser",new SendToUserConsoleCommand());
        consoleCommandMap.put("logout",new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup",new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel)
    {
        System.out.println("请输入指令：");
        String command = scanner.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if(consoleCommand!=null){
            consoleCommand.exec(scanner,channel);
        }
        else {
            System.err.println("无法识别[" + command + "]指令，请重新输入!");
        }
    }
}
