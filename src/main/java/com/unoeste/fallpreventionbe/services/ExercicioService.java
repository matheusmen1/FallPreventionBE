package com.unoeste.fallpreventionbe.services;

import com.unoeste.fallpreventionbe.entities.Exercicio;
import com.unoeste.fallpreventionbe.repositories.ExercicioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String DIRETORIO_UPLOADS_VIDEOS = "uploads/videos_exercicios/";
    private final String DIRETORIO_UPLOADS_FOTOS = "uploads/fotos_exercicios/";

    @PostConstruct
    public void inicializarColunasBanco() {
        try {
            jdbcTemplate.execute("ALTER TABLE paciente ADD COLUMN IF NOT EXISTS pac_pontuacao INTEGER DEFAULT 16");
            jdbcTemplate.execute("UPDATE paciente SET pac_pontuacao = 16 WHERE pac_pontuacao IS NULL");
            jdbcTemplate.execute("ALTER TABLE exercicio ADD COLUMN IF NOT EXISTS exe_dificuldade INTEGER DEFAULT 1");
            jdbcTemplate.execute("UPDATE exercicio SET exe_dificuldade = 1 WHERE exe_dificuldade IS NULL");
        } catch (Exception e) {
            System.err.println("Aviso ao verificar colunas pac_pontuacao / exe_dificuldade: " + e.getMessage());
        }
    }

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
            if (exercicio.getDificuldade() == null || exercicio.getDificuldade() < 1) {
                exercicio.setDificuldade(1);
            }
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

    public List<Exercicio> getAllByPontuacao(Integer pontuacao)
    {
        int pts = (pontuacao != null && pontuacao >= 16) ? pontuacao : 16;
        return exercicioRepository.getAllByPontuacao(pts);
    }

    public List<Exercicio> getAllByPontuacaoAndTipo(Integer pontuacao, Long tpeId)
    {
        int pts = (pontuacao != null && pontuacao >= 16) ? pontuacao : 16;
        return exercicioRepository.getAllByPontuacaoAndTipo(pts, tpeId);
    }
}
