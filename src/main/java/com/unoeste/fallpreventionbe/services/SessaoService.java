package com.unoeste.fallpreventionbe.services;

import com.unoeste.fallpreventionbe.entities.*;
import com.unoeste.fallpreventionbe.repositories.SessaoRepository;
import com.unoeste.fallpreventionbe.repositories.UsuarioRepository;
import com.unoeste.fallpreventionbe.webSocket.SessaoWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessaoService
{
    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SessaoWebSocketHandler webSocketHandler;

    public Sessao save(Sessao sessao)
    {
        try
        {
            List<SessaoFase> sessaoFases = sessao.getSessaoFases();

            if (sessao.getId() != null)
                sessaoRepository.deleteSessaoFase(sessao.getId());

            //sessao.setData_hora(LocalDateTime.now());
            sessao.setStatus("PENDENTE");
            sessao.setSessaoFases(null);
            sessao.setAprovacaoSessao(null);
            sessao.setResultadoSessao(null);
            Sessao novaSessao = sessaoRepository.save(sessao);



            for (SessaoFase sessaoFase : sessaoFases)
                sessaoRepository.addSessaoFase(sessaoFase.getOrdem(),sessao.getId(),sessaoFase.getExercicio().getId());
            return novaSessao;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Sessao getById(Long id)
    {
        return sessaoRepository.findById(id).orElse(null);
    }

    public List<Sessao> getAllByPaciente(Long id)
    {
        List<Sessao> sessaos = sessaoRepository.getAllByPaciente(id);
        return sessaos;
    }

    public Sessao cancelarSessaoPendente(Long id)
    {
        Sessao sessao = sessaoRepository.findById(id).orElse(null);
        if (sessao != null)
        {
            sessao.setStatus("CANCELADA");
            sessaoRepository.save(sessao);
            return sessao;
        }
        return null;
    }

    public AprovacaoSessao aprovarSessaoPendente(AprovacaoSessao aprovacaoSessao, Long id)
    {
        try{
            Sessao sessao = sessaoRepository.findById(id).orElse(null);
            if (sessao != null)
            {
                Usuario fisio = usuarioRepository.findById(aprovacaoSessao.getFisioterapeuta().getId()).orElse(null);

                if (fisio != null && fisio.getNivel() > 0)
                {
                    aprovacaoSessao.setData_hora(LocalDateTime.now());
                    aprovacaoSessao.setSessao(sessao);
                    aprovacaoSessao.setFisioterapeuta(fisio);

                    sessao.setStatus(aprovacaoSessao.getStatus().toUpperCase());
                    sessaoRepository.save(sessao);
                    if (sessao.getAprovacaoSessao() == null)
                        sessaoRepository.addAprovacaoSessao(aprovacaoSessao.getData_hora(), aprovacaoSessao.getFisioterapeuta().getId(), aprovacaoSessao.getStatus(), aprovacaoSessao.getMotivo(), sessao.getId());
                    else
                        sessaoRepository.updateAprovacaoSessao(aprovacaoSessao.getData_hora(), aprovacaoSessao.getFisioterapeuta().getId(), aprovacaoSessao.getStatus(), aprovacaoSessao.getMotivo(), sessao.getId());

                    return aprovacaoSessao;
                }
            }
            return null;
        }catch (Exception e){
            return null;
        }

    }

    public List<String> getCodigosUnity(Long id)
    {
        return sessaoRepository.getCodigoUnity(id);

    }

    public ResultadoSessao addResultadoSessao(ResultadoSessao resultadoSessao, Long id)
    {
        try{
            Sessao sessao = sessaoRepository.findById(id).orElse(null);
            if (sessao != null)
            {
                sessao.setStatus("CONCLUIDA");
                sessaoRepository.save(sessao);
                sessaoRepository.addResultadoSessao(resultadoSessao.getDuracao(), resultadoSessao.getObservacao(), sessao.getId());
                return resultadoSessao;
            }
            return null;
        }catch (Exception e)
        {
            return null;
        }
    }

    public List<Sessao> getAll()
    {
        return sessaoRepository.findAll();
    }

    public boolean delete(Long id)
    {
        try {
            if (sessaoRepository.existsById(id))
            {
                sessaoRepository.deleteSessaoObservacaoAll(id);
                sessaoRepository.deleteResultadoSessao(id);
                sessaoRepository.deleteAprovacaoSessao(id);
                sessaoRepository.deleteSessaoFase(id);
                sessaoRepository.deleteById(id);
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public boolean deleteObservacao(Long id)
    {
        try {

            sessaoRepository.deleteSessaoObservacao(id);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public List<Sessao> getAllByUsuarioId(Long id)
    {
        return sessaoRepository.getAllUsuarioById(id);
    }

    public List<Sessao> getAllByStatusById(String status, Long id)
    {
        return sessaoRepository.getAllByStatusById(status, id);
    }

    public List<Sessao> getAllPendenteByFisioterapeutaId(Long id)
    {
        return sessaoRepository.getAllPendenteByFisioterapeutaId(id);
    }

    public List<Sessao> getAllByStatus(String status)
    {
        return sessaoRepository.getAllByStatus(status);
    }

    public boolean iniciarSessao(Long id)
    {
        Sessao sessao = sessaoRepository.findById(id).orElse(null);
        if (sessao != null)
        {
            sessao.setStatus("EM_ANDAMENTO");
            sessao.setOrdemAtual(1);
            Exercicio exercicio = null;
            for (int i = 0; i < sessao.getSessaoFases().size(); i++)
            {
                if (sessao.getSessaoFases().get(i).getOrdem() == sessao.getOrdemAtual())
                    exercicio = sessao.getSessaoFases().get(i).getExercicio();
            }
            String comandoJson = String.format("{\"acao\": \"INICIAR_FASE\", \"package\": \"%s\", \"sessaoId\": %d}", exercicio.getCodigo_nome().trim(), null);
            if (webSocketHandler.enviarComandoParaUnity(comandoJson))
            {
                sessaoRepository.save(sessao);
                return true;
            }

        }
        return false;
    }

    public Sessao sairSala(Long id)
    {
        try
        {
            Sessao novaSessao = sessaoRepository.findById(id).orElse(null);
            if (novaSessao != null)
            {
                novaSessao.setStatus("APROVADA");
                novaSessao.setOrdemAtual(0);
                sessaoRepository.save(novaSessao);
                return novaSessao;
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean proximaFase(Long id)
    {
        Sessao sessao = sessaoRepository.findById(id).orElse(null);
        if (sessao != null)
        {
            sessao.setOrdemAtual(sessao.getOrdemAtual() + 1);
            Exercicio exercicio = null;
            for (int i = 0; i < sessao.getSessaoFases().size(); i++)
            {
                if (sessao.getSessaoFases().get(i).getOrdem() == sessao.getOrdemAtual())
                    exercicio = sessao.getSessaoFases().get(i).getExercicio();
            }
            String comandoJson = String.format("{\"acao\": \"PROXIMA_FASE\", \"package\": \"%s\", \"sessaoId\": %d}", exercicio.getCodigo_nome().trim(), null);
            if (webSocketHandler.enviarComandoParaUnity(comandoJson))
            {
                sessaoRepository.save(sessao);
                return true;
            }
        }
        return false;
    }

    public boolean pausarFase(Long id)
    {
        Sessao sessao = sessaoRepository.findById(id).orElse(null);
        if (sessao != null)
        {
            sessao.setStatus("PAUSADA");
            String comandoJson = String.format("{\"acao\": \"PAUSAR_FASE\", \"package\": \"%s\", \"sessaoId\": %d}", null, null);
            if (webSocketHandler.enviarComandoParaUnity(comandoJson))
            {
                sessaoRepository.save(sessao);
                return true;
            }
        }
        return false;
    }
    public boolean retomarFase(Long id)
    {
        Sessao sessao = sessaoRepository.findById(id).orElse(null);
        if (sessao != null)
        {
            sessao.setStatus("EM_ANDAMENTO");
            String comandoJson = String.format("{\"acao\": \"RETOMAR_FASE\", \"package\": \"%s\", \"sessaoId\": %d}", null, null);
            if (webSocketHandler.enviarComandoParaUnity(comandoJson))
            {
                sessaoRepository.save(sessao);
                return true;
            }

        }
        return false;
    }
    public boolean finalizarSessao(Long id)
    {
        Sessao sessao = sessaoRepository.findById(id).orElse(null);
        if (sessao != null)
        {
            String comandoJson = String.format("{\"acao\": \"FINALIZAR_SESSAO\", \"package\": \"%s\", \"sessaoId\": %d}", null, id);
            if (webSocketHandler.enviarComandoParaUnity(comandoJson))
            {
                sessaoRepository.save(sessao);
                return true;
            }
        }
        return false;
    }

    public SessaoObservacao addObservacao(SessaoObservacao sessaoObservacao, Long id)
    {
        try{
            Sessao sessao = sessaoRepository.findById(id).orElse(null);
            if (sessao != null)
            {
                sessaoObservacao.setSessao(sessao);
                sessaoObservacao.setData_hora(LocalDateTime.now());
                sessaoRepository.addObservacao(sessaoObservacao.getObservacao(), sessaoObservacao.getData_hora(), sessaoObservacao.getSessaoFase().getId(), id);
                return sessaoObservacao;
            }
            return null;
        }catch (Exception e)
        {
            return null;
        }
    }

    public List<SessaoObservacao> getAllObservacoesByPacienteAndSessao(Long idSessao, Long idPaciente)
    {
        return sessaoRepository.getAllObservacoesByPacienteAndSessao(idSessao, idPaciente);
    }
}
