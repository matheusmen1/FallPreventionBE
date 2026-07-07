package com.unoeste.fallpreventionbe.restControllers;

import com.unoeste.fallpreventionbe.entities.Erro;
import com.unoeste.fallpreventionbe.entities.Paciente;
import com.unoeste.fallpreventionbe.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/paciente")
public class PacienteRestControllers
{
    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<Paciente> pacienteList = pacienteService.getAll();
        if (pacienteList.size() > 0)
            return ResponseEntity.ok().body(pacienteList);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Paciente Cadastrado"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id)
    {
        Paciente paciente = pacienteService.getById(id);
        if (paciente != null)
            return ResponseEntity.ok().body(paciente);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Encontrar Paciente"));
    }

    @GetMapping("/getByCpf/{cpf}")
    public ResponseEntity<Object> getByEmail(@PathVariable("cpf") String cpf)
    {
        Paciente paciente = pacienteService.getByCPF(cpf);
        if (paciente != null)
            return ResponseEntity.ok().body(paciente);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Encontrar Paciente"));
    }
    @GetMapping("/getByNome/{nome}")
    public ResponseEntity<Object> getByNome(@PathVariable("nome") String nome)
    {
        Paciente paciente = pacienteService.getByNome(nome);
        if (paciente != null)
            return ResponseEntity.ok().body(paciente);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Encontrar Paciente"));
    }
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Paciente paciente)
    {
        Paciente novoPaciente = pacienteService.save(paciente);
        if (novoPaciente != null)
            return ResponseEntity.ok().body(novoPaciente);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Cadastrar Paciente"));
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Paciente paciente)
    {
        Paciente novoPaciente = pacienteService.save(paciente);
        if (novoPaciente != null)
            return ResponseEntity.ok().body(novoPaciente);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Alterar Paciente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id)
    {
        if (pacienteService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Deletar Paciente"));
    }

}
