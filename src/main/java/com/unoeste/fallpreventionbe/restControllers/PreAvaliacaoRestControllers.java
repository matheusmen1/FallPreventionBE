package com.unoeste.fallpreventionbe.restControllers;

import com.unoeste.fallpreventionbe.entities.Erro;
import com.unoeste.fallpreventionbe.entities.PreAvaliacao;
import com.unoeste.fallpreventionbe.services.PreAvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apis/pre-avaliacao")
public class PreAvaliacaoRestControllers
{
    @Autowired
    private PreAvaliacaoService preAvaliacaoService;

    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<PreAvaliacao> preAvaliacaoList = preAvaliacaoService.getAll();
        if (preAvaliacaoList.size() > 0)
            return ResponseEntity.ok().body(preAvaliacaoList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma Pré-Avaliação Encontrada"));
    }
    @GetMapping("getAllByPaciente/{idPaciente}")
    public ResponseEntity<Object> getAllbyPaciente(@PathVariable("id") Long id)
    {
        List<PreAvaliacao> preAvaliacaoList = preAvaliacaoService.getAllByPaciente(id);
        if (preAvaliacaoList.size() > 0)
            return ResponseEntity.ok().body(preAvaliacaoList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma Pré-Avaliação Encontrada"));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id)
    {
        PreAvaliacao preAvaliacao = preAvaliacaoService.getById(id);
        if (preAvaliacao != null)
            return ResponseEntity.ok().body(preAvaliacao);
        else
            return ResponseEntity.badRequest().body(new Erro("Pré-Avaliação Não Encontrada"));

    }
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody PreAvaliacao preAvaliacao)
    {
        PreAvaliacao novaPreAvaliacao = preAvaliacaoService.save(preAvaliacao);
        if (novaPreAvaliacao != null)
            return ResponseEntity.ok().body(novaPreAvaliacao);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Pré-Avaliação"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id)
    {
        if (preAvaliacaoService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Pré-Avaliação"));
    }

}
