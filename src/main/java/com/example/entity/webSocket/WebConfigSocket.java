package com.example.entity.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.InetSocketAddress;
import java.util.Map;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebConfigSocket implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket")
                .addInterceptors(httpSessionHandshakeInterceptor());
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Bean
    public HandshakeInterceptor httpSessionHandshakeInterceptor() {
        return new HandshakeInterceptor() {

            @Override
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                InetSocketAddress remoteAddress = request.getRemoteAddress();
                log.info("---- remoteAddress ----, hostname: {}, hostString, {}", remoteAddress.getHostName(), remoteAddress.getHostString());

                return true; // true: websocket打开， false: 关闭
            }

            @Override
            public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

            }
        };

    }

}
