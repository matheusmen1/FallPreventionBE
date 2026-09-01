package com.unoeste.fallpreventionbe.services;

import com.unoeste.fallpreventionbe.entities.Exercicio;
import com.unoeste.fallpreventionbe.repositories.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ExercicioService
{

    @Autowired
    private ExercicioRepository exercicioRepository;
    private final String DIRETORIO_UPLOADS_VIDEOS = "uploads/videos_exercicios/";
    private final String DIRETORIO_UPLOADS_FOTOS = "uploads/fotos_exercicios/";
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

    public Exercicio save(Exercicio exercicio, MultipartFile video, MultipartFile foto)
    {
        try{
            if (video != null)
            {
                String novoArquivo = UUID.randomUUID()+"_"+video.getOriginalFilename();
                Path caminho = Paths.get(DIRETORIO_UPLOADS_VIDEOS + novoArquivo);
                Files.createDirectories(caminho.getParent());
                Files.write(caminho, video.getBytes());
                exercicio.setUrl_video("/videos_exercicios/"+novoArquivo);
            }
            if (foto != null)
            {
                String novoArquivo = UUID.randomUUID()+"_"+foto.getOriginalFilename();
                Path caminho = Paths.get(DIRETORIO_UPLOADS_FOTOS + novoArquivo);
                Files.createDirectories(caminho.getParent());
                Files.write(caminho, foto.getBytes());
                exercicio.setUrl_foto("/fotos_exercicios/"+novoArquivo);
            }

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

    public List<Exercicio> getAllByName(String nome)
    {
        String novoNome = nome + "%";
        return exercicioRepository.getAllByName(novoNome);
    }
}
