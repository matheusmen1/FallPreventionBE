package com.unoeste.fallpreventionbe.restControllers;

import com.unoeste.fallpreventionbe.entities.Erro;
import com.unoeste.fallpreventionbe.entities.TipoExercicio;
import com.unoeste.fallpreventionbe.services.TipoExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apis/tipo-exercicio")
@CrossOrigin
public class TipoExercicioRestControllers
{
    @Autowired
    private TipoExercicioService tipoExercicioService;

    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<TipoExercicio> tipoExercicios = tipoExercicioService.getAll();
        if (tipoExercicios.size() > 0)
            return ResponseEntity.ok().body(tipoExercicios);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Tipo de Exercício Encontrado"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id)
    {
        TipoExercicio tipoExercicio = tipoExercicioService.getById(id);
        if (tipoExercicio != null)
            return ResponseEntity.ok().body(tipoExercicio);
        else
            return ResponseEntity.badRequest().body(new Erro("Tipo Exercicio Não Encontrado"));
    }
    @GetMapping("/getAllByName/{nome}")
    public ResponseEntity<Object> getAllByName(@PathVariable("nome") String nome)
    {
        List<TipoExercicio> tipoExercicios = tipoExercicioService.getAllByName(nome);
        if (tipoExercicios != null)
            return ResponseEntity.ok(tipoExercicios);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Tipo de Exercício Encontrado"));
    }
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody TipoExercicio tipoExercicio)
    {
        TipoExercicio tipoExercicioNovo = tipoExercicioService.save(tipoExercicio);
        if (tipoExercicioNovo != null)
            return ResponseEntity.ok().body(tipoExercicioNovo);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Tipo Exercicio"));
    }
    @PutMapping
    public ResponseEntity<Object> alterar(@RequestBody TipoExercicio tipoExercicio)
    {
        TipoExercicio tipoExercicioNovo = tipoExercicioService.save(tipoExercicio);
        if (tipoExercicioNovo != null)
            return ResponseEntity.ok().body(tipoExercicioNovo);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Alterar Tipo Exercicio"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id)
    {
        if (tipoExercicioService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Tipo Exercicio"));
    }





}
