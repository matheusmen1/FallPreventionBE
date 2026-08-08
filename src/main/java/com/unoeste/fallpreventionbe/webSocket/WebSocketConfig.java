package com.unoeste.fallpreventionbe.webSocket;

import com.unoeste.fallpreventionbe.webRTC.WebRTCHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private SessaoWebSocketHandler sessaoWebSocketHandler;
    @Autowired
    private WebRTCHandler webRTCHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
    {
        registry.addHandler(sessaoWebSocketHandler, "/ws/sessao").setAllowedOrigins("*");
        registry.addHandler(webRTCHandler, "/ws/webrtc").setAllowedOrigins("*");
    }
}