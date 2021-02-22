package com.github.xingshuangs.netty.demo.server;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @author xingshuang
 * @date 2020/11/10
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 每当从客户端收到新的数据时，这个方法会在收到消息时被调用
      * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到数据：" + ((ByteBuf) msg).toString(Charset.defaultCharset()));
        ctx.write(Unpooled.wrappedBuffer("Server message".getBytes()));
        ctx.fireChannelRead(msg);
    }

    /**
     * 数据读取完后被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 当Netty由于IO错误或者处理器在处理事件时抛出的异常时被调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
