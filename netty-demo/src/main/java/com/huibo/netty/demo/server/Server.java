package com.huibo.netty.demo.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author xingshuang
 * @date 2020/11/10
 */
public class Server {

    public static void main(String[] args) {
        // accept线程组，用来接受连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // I/O线程组， 用于处理业务逻辑
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);

        try {
            // 服务端启动引导
            ServerBootstrap b = new ServerBootstrap();
            // 绑定两个线程组, 设置reactor 线程
            b.group(bossGroup, workerGroup)
                    // 指定通道类型, 设置nio类型的channel
                    .channel(NioServerSocketChannel.class)
                    .localAddress(8081)
                    // 设置TCP连接的缓冲区
                    .option(ChannelOption.SO_BACKLOG, 100)
                    // 设置日志级别
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 有连接到达时会创建一个channel
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            // 获取处理器链
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 添加新的件处理器, pipeline管理channel中的Handler, 在channel队列中添加一个handler来处理业务
                            pipeline.addLast(new EchoServerHandler());
                        }
                    });

            // 通过bind启动服务
            ChannelFuture f = b.bind(8081).sync();
            // 阻塞主线程，直到网络服务被关闭
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
