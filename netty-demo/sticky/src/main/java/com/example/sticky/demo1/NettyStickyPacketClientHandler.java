package com.example.sticky.demo1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NettyStickyPacketClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger(NettyStickyPacketClientHandler.class);
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private byte[] pingBytes;

    public NettyStickyPacketClientHandler() {
        pingBytes = ("hello, i am netty client."+LINE_SEPARATOR).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf byteBuf = null;
        for(int i = 0; i < 120; i++) {
            byteBuf = Unpooled.buffer(pingBytes.length);
            byteBuf.writeBytes(pingBytes);
            ctx.writeAndFlush(byteBuf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String response = new String(bytes,"UTF-8");
        response = response.substring(0,response.length() - LINE_SEPARATOR.length());
        logger.info("netty client received response: " + response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
