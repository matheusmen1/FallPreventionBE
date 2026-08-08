package com.unoeste.fallpreventionbe.webRTC;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class WebRTCHandler extends TextWebSocketHandler
{
    private final List<WebSocketSession> sessoes = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception
    {
        sessoes.add(session);
        System.out.println("Novo Cliente de Vídeo Conectado: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        for (WebSocketSession socketSession : sessoes)
        {
            if (socketSession.isOpen() && !socketSession.getId().equals(session.getId())) {
                socketSession.sendMessage(new TextMessage(message.getPayload()));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessoes.remove(session);
        System.out.println("Cliente de Vídeo Desconectado: " + session.getId());
    }
}