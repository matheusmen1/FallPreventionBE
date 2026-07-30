package com.unoeste.fallpreventionbe.services;

import com.unoeste.fallpreventionbe.entities.Metrica;
import com.unoeste.fallpreventionbe.entities.FichaAvaliacao;
import com.unoeste.fallpreventionbe.repositories.FichaAvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FichaAvaliacaoService {

    @Autowired
    private FichaAvaliacaoRepository fichaAvaliacaoRepository;

    public List<FichaAvaliacao> getAll()
    {
        return fichaAvaliacaoRepository.findAll();
    }

    public List<FichaAvaliacao> getAllByPaciente(Long id)
    {
        return fichaAvaliacaoRepository.getAllByPaciente(id);
    }

    public FichaAvaliacao getById(Long id)
    {
        return fichaAvaliacaoRepository.findById(id).orElse(null);
    }

    public FichaAvaliacao save(FichaAvaliacao preAvaliacao)
    {
        try{
            preAvaliacao.setData_avaliacao(LocalDateTime.now());
            FichaAvaliacao novaPreAvaliacao = fichaAvaliacaoRepository.save(preAvaliacao);
            List<Metrica> metricas = novaPreAvaliacao.getMetricas();
            for (Metrica metrica : metricas)
                fichaAvaliacaoRepository.addMetrica(metrica.getNome_teste(), metrica.getPontuacao(), novaPreAvaliacao.getId());

            return novaPreAvaliacao;
        }catch (Exception e){
            return null;
        }
    }

    public boolean delete(Long id)
    {
        try{
            if (fichaAvaliacaoRepository.existsById(id))
            {
                fichaAvaliacaoRepository.deleteMetrica(id);
                fichaAvaliacaoRepository.deleteById(id);
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
