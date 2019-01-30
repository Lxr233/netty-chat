package org.lxr.nettychatclient.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.lxr.nettychatclient.console.ConsoleCommandManager;
import org.lxr.nettychatclient.console.LoginConsoleCommand;
import org.lxr.protocal.packet.request.LoginRequestPacket;
import org.lxr.protocal.packet.request.MessageRequestPacket;
import org.lxr.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NettyChatClient
{

    private static final Logger log = LoggerFactory.getLogger(NettyChatClient.class);

    @Value("${tcp.port}")
    private int port;

    @Value("${tcp.url}")
    private String url;

    @Value("${tcp.timeout.millis}")
    private int timeOut;

    @Value("${tcp.maxretry}")
    private int maxRetry;

    @Autowired
    private MyChannelInitializer myChannelInitializer;

    public void start(){

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeOut)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(myChannelInitializer);
        connect(bootstrap, maxRetry);
    }

    private void connect(Bootstrap bootstrap , int retry) {
        bootstrap.connect(url, port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("连接成功!");
                System.out.println("连接成功,启动控制台......");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                log.info("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (maxRetry - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                log.info(new Date() + ": 连接失败，第" + order + "次重连……");

                /**
                 * bootstrap.config() 这个方法返回的是 BootstrapConfig，他是对 Bootstrap 配置参数的抽象，
                 * 然后 bootstrap.config().group() 返回的就是我们在一开始的时候配置的线程模型 workerGroup，
                 * 调用 workerGroup 的 schedule 方法即可实现定时任务逻辑
                 */
                bootstrap.config().group().schedule(() -> connect(bootstrap, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private void startConsoleThread(Channel channel)
    {
        Scanner scanner = new Scanner(System.in);
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();

        new Thread(() -> {
            //是否进行了登录操作
            boolean hasLogin=false;
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel) && !hasLogin) {
                    loginConsoleCommand.exec(scanner,channel);
                    hasLogin=true;
                }
                else if(hasLogin && !SessionUtil.hasLogin(channel)){
                    //进行了登录，还没响应登录成功
                    try {
                        System.out.println("等待服务端登录响应...");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        log.error("InterruptedException ",e);
                    }
                }
                else {
                    consoleCommandManager.exec(scanner,channel);
                }
            }
        }).start();
    }
}
