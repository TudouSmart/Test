package org.tudou.example.time;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by hugang on 2017/7/29.
 */
public class TimerClient {

    public void bind(final int port) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap start = new ServerBootstrap();
        start.group(group)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new TimeServerHandler());
                    }
                });
        try {
            ChannelFuture future = start.bind(port).sync();
            future.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new TimerClient().bind(8080);
    }
}
