package com.unoeste.fallpreventionbe.restControllers;

import com.unoeste.fallpreventionbe.entities.Doenca;
import com.unoeste.fallpreventionbe.entities.Erro;
import com.unoeste.fallpreventionbe.services.DoencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/doenca")
@CrossOrigin
public class DoencaRestController
{
    @Autowired
    private DoencaService doencaService;

    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<Doenca> doencas = doencaService.getAll();
        if (doencas != null)
            return ResponseEntity.ok().body(doencas);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Encontrar Doenças"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id)
    {
        Doenca doenca = doencaService.getById(id);
        if (doenca != null)
            return ResponseEntity.ok().body(doenca);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Encontrar Doença"));

    }
    @GetMapping("/getAllByName/{nome}")
    public ResponseEntity<Object> getAllByName(@PathVariable("nome") String nome)
    {
        List<Doenca> doencas = doencaService.getAllByName(nome);
        if (doencas != null)
            return ResponseEntity.ok().body(doencas);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Encontrar Doenças"));
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Doenca doenca)
    {
        Doenca novaDoenca = doencaService.save(doenca);
        if (novaDoenca != null)
            return ResponseEntity.ok().body(doenca);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Cadastrar Doença"));
    }
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Doenca doenca)
    {
        Doenca novaDoenca =  doencaService.save(doenca);
        if (novaDoenca != null)
            return ResponseEntity.ok().body(doenca);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Alterar Doença"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id)
    {
        if (doencaService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Doença"));
    }


}
