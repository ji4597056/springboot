package com.study.spring.common.websocket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketClientCompressionHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import java.net.URI;
import javax.net.ssl.SSLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

/**
 * websocket forward client
 *
 * @author Jeffrey
 * @since 2017/11/28 13:48
 */
public class WebsocketForwardClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketForwardClient.class);

    /**
     * ws scheme
     */
    private static final String WS_SCHEME = "ws";

    /**
     * wss scheme
     */
    private static final String WSS_SCHEME = "wss";

    /**
     * forward url,eg:"ws://127.0.0.1:8080/websocket"
     */
    private final String url;

    /**
     * server websocket session
     */
    private final WebSocketSession webSocketSession;

    /**
     * bootstrap
     */
    private Bootstrap bootstrap;

    /**
     * chanel
     */
    private Channel channel;

    private WebsocketForwardClient(String url, WebSocketSession webSocketSession) {
        this.url = url;
        this.webSocketSession = webSocketSession;
    }

    /**
     * 建立连接
     *
     * @throws Exception Exception
     */
    public void connect() throws Exception {
        URI uri = new URI(url);
        String scheme = uri.getScheme();
        final String host = uri.getHost();
        final int port = uri.getPort();
        // check
        if (!WS_SCHEME.equalsIgnoreCase(scheme) && !WSS_SCHEME.equalsIgnoreCase(scheme)) {
            LOGGER.error("Error websocket scheme!url: {}", url);
            throw new IllegalArgumentException("Only ws(s) is supported.");
        }

        final SslContext sslCtx = getSslContext(scheme);

        EventLoopGroup group = new NioEventLoopGroup();
        final WebSocketForwardClientHandler handler =
            new WebSocketForwardClientHandler(WebSocketClientHandshakerFactory
                .newHandshaker(uri, WebSocketVersion.V13, null, true, new DefaultHttpHeaders()),
                webSocketSession);
        // create
        bootstrap = new Bootstrap();
        bootstrap.group(group)
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ChannelPipeline p = ch.pipeline();
                    if (sslCtx != null) {
                        p.addLast(sslCtx.newHandler(ch.alloc(), host, port));
                    }
                    p.addLast(
                        new HttpClientCodec(),
                        new HttpObjectAggregator(8192),
                        WebSocketClientCompressionHandler.INSTANCE,
                        handler);
                }
            });
        // connect
        channel = bootstrap.connect(uri.getHost(), port).sync().channel();
        handler.handshakeFuture().sync();
    }

    /**
     * create WebsocketForwardClient
     *
     * @param url url
     * @param webSocketSession webSocketSession
     * @return WebsocketForwardClient
     */
    public static WebsocketForwardClient create(String url, WebSocketSession webSocketSession) {
        return new WebsocketForwardClient(url, webSocketSession);
    }

    /**
     * 获取SslContext
     *
     * @param scheme scheme
     * @return SslContext
     */
    private SslContext getSslContext(String scheme) throws SSLException {
        boolean ssl = WSS_SCHEME.equalsIgnoreCase(scheme);
        if (ssl) {
            return SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        }
        return null;
    }

    public String getUrl() {
        return url;
    }

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public Channel getChannel() {
        return channel;
    }
}
