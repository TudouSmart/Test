package org.tudou.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by hugang on 2017/7/29.
 */
public class ClientDemo {
    public static void main(String[] args) throws Exception{
        Bootstrap start = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        start.group(group)
            .channel(NioSocketChannel.class)
            .remoteAddress(new InetSocketAddress("localhost", 8080))
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel s) throws Exception {
                    ChannelPipeline p = s.pipeline();
                    p.addLast(new ClientHandler());
                }
            });

        try {
            ChannelFuture f = start.connect().sync();
            f.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }

    }
}
