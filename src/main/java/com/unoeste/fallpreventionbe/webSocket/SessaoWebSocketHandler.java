package com.unoeste.fallpreventionbe.webSocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SessaoWebSocketHandler extends TextWebSocketHandler
{

    private final CopyOnWriteArrayList<WebSocketSession> sessoesConectadas = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession sessao) throws Exception
    {
        sessoesConectadas.add(sessao);
        System.out.println("Novo capacete conectado! ID: " + sessao.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession sessao, CloseStatus status) throws Exception
    {
        sessoesConectadas.remove(sessao);
        System.out.println("Capacete desconectado. ID: " + sessao.getId());
    }

    public boolean enviarComandoParaUnity(String jsonComando)
    {
        for (WebSocketSession sessao : sessoesConectadas)
        {
            if (sessao.isOpen())
            {
                try
                {
                    sessao.sendMessage(new TextMessage(jsonComando));
                    return true;
                } catch (IOException e)
                {
                    System.err.println("Erro ao Enviar Comando Via WebSocket: " + e.getMessage());
                    return false;
                }
            }
        }
        return false;
    }
}