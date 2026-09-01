package com.unoeste.fallpreventionbe.services;

import com.unoeste.fallpreventionbe.entities.Paciente;
import com.unoeste.fallpreventionbe.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class PacienteService
{
    @Autowired
    PacienteRepository pacienteRepository;

    public List<Paciente> getAll()
    {
        try {
            List<Paciente> pacienteList = pacienteRepository.findAll();
            return pacienteList;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    public Paciente getById(Long id)
    {
        return pacienteRepository.findById(id).orElse(null);
    }

    public Paciente getByCPF(String cpf)
    {
        return pacienteRepository.getByEmail(cpf);
    }
    public Paciente save(Paciente paciente)
    {
        try{
            if (paciente.getId() == null) // ainda nao criado
            {
                Paciente aux = pacienteRepository.getByCpfOrEmail(paciente.getCpf(), paciente.getEmail());
                if (aux != null)
                    return null;
            }

            LocalDate hoje = LocalDate.now();
            int idade = Period.between(paciente.getData_nascimento(), hoje).getYears();
            if (idade >= 12 && idade < 18)
                paciente.setTipo_pessoa("Adolescente");
            else
            if (idade >= 18 && idade <= 59)
            {
                paciente.setTipo_pessoa("Adulto");
            }
            else
            if (idade >= 60)
            {
                paciente.setTipo_pessoa("Idoso");
            }
            else
            {
                paciente.setTipo_pessoa("Indefinido");
            }
            return pacienteRepository.save(paciente);

        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(Long id)
    {
        try{
            Paciente paciente = pacienteRepository.findById(id).orElse(null);
            if (paciente != null)
            {
                pacienteRepository.delete(paciente);
                return true;
            }
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }

    public List<Paciente> getByNome(String nome)
    {
        String novoNome =  nome + '%';
        return pacienteRepository.getByNome(novoNome);
    }
}
