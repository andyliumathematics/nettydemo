package com.ll.netty.client;

import java.time.LocalDate;
import java.util.Date;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup).channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<SocketChannel>(){
            public void initChannel(SocketChannel ch){
                ch.pipeline().addLast(new NettyClientHandler());
            }
        });

        ChannelFuture future = bootstrap.connect("127.0.0.1",8756).sync();
        future.addListener(future1 -> {
            if (future1.isSuccess())
                System.out.println("成功"+new Date().toString());
            else
                System.err.println("链接失败");
        });
    }
}
