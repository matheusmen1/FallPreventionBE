package com.unoeste.fallpreventionbe.restControllers;

import com.unoeste.fallpreventionbe.entities.Erro;
import com.unoeste.fallpreventionbe.entities.Exercicio;
import com.unoeste.fallpreventionbe.services.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/exercicio")
@CrossOrigin
public class ExercicioRestControllers
{
    @Autowired
    private ExercicioService exercicioService;

    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<Exercicio> exercicios = exercicioService.getAll();
        if (exercicios.size() > 0)
            return ResponseEntity.ok().body(exercicios);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Exercício Encontrado"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id)
    {
        Exercicio exercicio = exercicioService.getById(id);
        if (exercicio != null)
            return ResponseEntity.ok().body(exercicio);
        else
            return ResponseEntity.badRequest().body(new Erro("Exercicio Não Encontrado"));
    }
    @GetMapping("/getAllByName/{nome}")
    public ResponseEntity<Object> getAllByName(@PathVariable("nome") String nome)
    {
        List<Exercicio> exercicios = exercicioService.getAllByName(nome);
        if (exercicios != null)
            return ResponseEntity.ok(exercicios);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Exercício Encontrado"));
    }
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Exercicio exercicio)
    {
        Exercicio novoExercicio = exercicioService.save(exercicio);
        if (novoExercicio != null)
            return ResponseEntity.ok().body(novoExercicio);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Exercicio"));
    }
    @PutMapping
    public ResponseEntity<Object> alterar(@RequestBody Exercicio exercicio)
    {
        Exercicio novoExercicio = exercicioService.save(exercicio);
        if (novoExercicio != null)
            return ResponseEntity.ok().body(novoExercicio);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Alterar Exercicio"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id)
    {
        if (exercicioService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Exercicio"));
    }
}
