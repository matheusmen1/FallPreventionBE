package com.unoeste.fallpreventionbe.services;

import com.unoeste.fallpreventionbe.entities.Metrica;
import com.unoeste.fallpreventionbe.entities.PreAvaliacao;
import com.unoeste.fallpreventionbe.repositories.PreAvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PreAvaliacaoService {

    @Autowired
    private PreAvaliacaoRepository preAvaliacaoRepository;

    public List<PreAvaliacao> getAll()
    {
        return preAvaliacaoRepository.findAll();
    }

    public List<PreAvaliacao> getAllByPaciente(Long id)
    {
        return preAvaliacaoRepository.getAllByPaciente(id);
    }

    public PreAvaliacao getById(Long id)
    {
        return preAvaliacaoRepository.findById(id).orElse(null);
    }

    public PreAvaliacao save(PreAvaliacao preAvaliacao)
    {
        try{
            preAvaliacao.setData_avaliacao(LocalDateTime.now());
            PreAvaliacao novaPreAvaliacao = preAvaliacaoRepository.save(preAvaliacao);
            List<Metrica> metricas = novaPreAvaliacao.getMetricas();
            for (Metrica metrica : metricas)
                preAvaliacaoRepository.addMetrica(metrica.getNome_teste(), metrica.getPontuacao(), novaPreAvaliacao.getId());

            return novaPreAvaliacao;
        }catch (Exception e){
            return null;
        }
    }

    public boolean delete(Long id)
    {
        try{
            if (preAvaliacaoRepository.existsById(id))
            {
                preAvaliacaoRepository.deleteMetrica(id);
                preAvaliacaoRepository.deleteById(id);
                return true;
            }
            else
                return false;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
