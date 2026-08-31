package com.unoeste.fallpreventionbe.webSocket;

import com.unoeste.fallpreventionbe.entities.Pacote;
import com.unoeste.fallpreventionbe.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ReactWebSocketHandler extends TextWebSocketHandler
{

    private final CopyOnWriteArrayList<WebSocketSession> sessoesConectadas = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void afterConnectionEstablished(WebSocketSession sessao) throws Exception
    {
        sessoesConectadas.add(sessao);
        System.out.println("Novo react web conectado! ID: " + sessao.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession sessao, CloseStatus status) throws Exception
    {
        sessoesConectadas.remove(sessao);
        System.out.println("React web desconectado. ID: " + sessao.getId());
    }

    public void avisarReact(String acao)
    {
        Pacote pacote = new Pacote();
        pacote.setAcao(acao);
        String json = objectMapper.writeValueAsString(pacote);
        for (WebSocketSession sessao : sessoesConectadas)
        {
            if (sessao.isOpen())
            {
                try
                {
                    sessao.sendMessage(new TextMessage(json));
                } catch (IOException e)
                {
                    System.err.println("Erro ao Enviar Comando Via WebSocket: " + e.getMessage());
                }
            }
        }
    }
}
