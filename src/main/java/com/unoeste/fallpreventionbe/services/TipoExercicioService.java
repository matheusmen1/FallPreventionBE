package com.unoeste.fallpreventionbe.services;
import com.unoeste.fallpreventionbe.entities.TipoExercicio;
import com.unoeste.fallpreventionbe.repositories.TipoExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoExercicioService
{

    @Autowired
    private TipoExercicioRepository tipoExercicioRepository;
    public List<TipoExercicio> getAll()
    {
        return tipoExercicioRepository.findAll();
    }

    public TipoExercicio getById(Long id)
    {
        TipoExercicio tipoExercicio = tipoExercicioRepository.findById(id).orElse(null);
        if (tipoExercicio != null)
            return tipoExercicio;
        else
            return null;
    }

    public TipoExercicio save(TipoExercicio tipoExercicio)
    {
        try{
            return tipoExercicioRepository.save(tipoExercicio);

        }catch (Exception e){
            return null;
        }
    }
    public boolean delete(Long id)
    {
        try{
            TipoExercicio tipoExercicio = tipoExercicioRepository.findById(id).orElse(null);
            if (tipoExercicio != null)
            {
                tipoExercicioRepository.delete(tipoExercicio);
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }

    }


}
