package com.unoeste.fallpreventionbe.services;

import com.unoeste.fallpreventionbe.entities.*;
import com.unoeste.fallpreventionbe.repositories.SessaoRepository;
import com.unoeste.fallpreventionbe.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessaoService
{
    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

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

    public List<Sessao> getAllByUsuarioId(Long id)
    {
        return sessaoRepository.getAllUsuarioById(id);
    }

    public List<Sessao> getAllByStatus(String status, Long id)
    {
        return sessaoRepository.getAllByStatus(status, id);
    }

    public List<Sessao> getAllPendenteByFisioterapeutaId(Long id)
    {
        return sessaoRepository.getAllPendenteByFisioterapeutaId(id);
    }
}
