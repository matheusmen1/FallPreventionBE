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
public class SessaoWebSocketHandler extends TextWebSocketHandler
{
    @Autowired
    @Lazy
    private SessaoService sessaoService;

    @Autowired
    private ReactWebSocketHandler reactWebSocketHandler;
    private final CopyOnWriteArrayList<WebSocketSession> sessoesConectadas = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
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

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception
    {

        String payload = message.getPayload();

        Pacote pacote = objectMapper.readValue(payload, Pacote.class);

        switch (pacote.getAcao()) {
            case "FINALIZAR_SESSAO_UNITY":
                sessaoService.addResultado(pacote);
                sessaoService.finalizarSessaoUnity(session, pacote.getSessao_id());
                reactWebSocketHandler.avisarReact("FINALIZAR_SESSAO_REACT");
                break;
            case "PROXIMA_FASE":
                sessaoService.addResultado(pacote);
                sessaoService.proximaFaseUnity(session, pacote.getSessao_id());
                reactWebSocketHandler.avisarReact(pacote.getAcao()+"_REACT");
                break;
            case "HABILITAR_BOTAO_REINICIAR_INTERVENCAO":
                reactWebSocketHandler.avisarReact(pacote.getAcao());
                break;
            case "HABILITAR_BOTAO_INICIAR":
                reactWebSocketHandler.avisarReact(pacote.getAcao());
                break;
        }
    }



}