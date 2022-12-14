package com.example.sticky.demo2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NettyStickyPacketClient {

    private static final Logger logger = LogManager.getLogger(NettyStickyPacketClient.class);
    //private static final int RESPONSE_BYTES_LENGTH = 56;

    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 8998;

    private String host;
    private int port;

    public NettyStickyPacketClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws Exception {
        logger.info("netty client is connecting to server[" + host + ":" + port + "].");

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new LineBasedFrameDecoder(1024))
//                                    .addLast(new DelimiterBasedFrameDecoder(
//                                            1024,
//                                            Unpooled.copiedBuffer(DELIMITER.getBytes())))
//                                    .addLast(new FixedLengthFrameDecoder(RESPONSE_BYTES_LENGTH))
                                    .addLast(new StringDecoder())
                                    .addLast(new NettyStickyPacketClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            logger.info("server connected: " + host + ":" + port + ".");
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        NettyStickyPacketClient client = new NettyStickyPacketClient(
                DEFAULT_HOST, DEFAULT_PORT);
        client.connect();
    }

}
