package com.unoeste.fallpreventionbe.services;

import com.unoeste.fallpreventionbe.entities.Exercicio;
import com.unoeste.fallpreventionbe.repositories.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercicioService
{
    @Autowired
    private ExercicioRepository exercicioRepository;
    public List<Exercicio> getAll()
    {
        return exercicioRepository.findAll();
    }

    public Exercicio getById(Long id)
    {
        Exercicio exercicio = exercicioRepository.findById(id).orElse(null);
        if (exercicio != null)
            return exercicio;
        else
            return null;
    }

    public Exercicio save(Exercicio exercicio)
    {
        try{
            return exercicioRepository.save(exercicio);

        }catch (Exception e){
            return null;
        }
    }
    public boolean delete(Long id)
    {
        try{
            Exercicio exercicio = exercicioRepository.findById(id).orElse(null);
            if (exercicio != null)
            {
                exercicioRepository.delete(exercicio);
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }

    }
}
