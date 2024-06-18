package com.example.entity.webSocket.fromBiLiBiLi;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class MyWsConfig implements WebSocketConfigurer {

    @Resource
    private MyWsHandler myWsHandler;

    @Resource
    private MyWsInterceptor myWsInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWsHandler, "myWs")
                .addInterceptors(myWsInterceptor)
                .setAllowedOriginPatterns("*");
    }

}
