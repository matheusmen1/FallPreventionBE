package com.unoeste.fallpreventionbe.services;

import com.unoeste.fallpreventionbe.entities.Doenca;
import com.unoeste.fallpreventionbe.repositories.DoencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoencaService
{
    @Autowired
    private DoencaRepository doencaRepository;
    public List<Doenca> getAll()
    {
        return doencaRepository.findAll();
    }

    public Doenca getById(Long id)
    {
        return doencaRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id)
    {
        try{
            Doenca doenca = doencaRepository.findById(id).orElse(null);
            if (doenca != null)
            {
                doencaRepository.delete(doenca);
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }

    public Doenca save(Doenca doenca)
    {
        try {
            return doencaRepository.save(doenca);
        }catch (Exception e){
            return null;
        }
    }

    public List<Doenca> getAllByName(String nome)
    {
        return doencaRepository.getAllByName(nome+"%");
    }
}
