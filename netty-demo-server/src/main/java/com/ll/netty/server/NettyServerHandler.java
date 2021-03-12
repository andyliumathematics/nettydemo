package com.ll.netty.server;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);
        String req = new String(bytes, "utf-8");
        System.out.println("客户端请求：" + req);
        String res = "helloworld>>>client";
        ByteBuf buf = getByteBuf(ctx, res);
        ctx.writeAndFlush(buf);

    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx, String str) {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = str.getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }
}
