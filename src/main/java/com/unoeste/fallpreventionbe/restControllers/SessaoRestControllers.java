package com.unoeste.fallpreventionbe.restControllers;

import com.unoeste.fallpreventionbe.entities.AprovacaoSessao;
import com.unoeste.fallpreventionbe.entities.Erro;
import com.unoeste.fallpreventionbe.entities.ResultadoSessao;
import com.unoeste.fallpreventionbe.entities.Sessao;
import com.unoeste.fallpreventionbe.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/sessao")
@CrossOrigin
public class SessaoRestControllers
{
    @Autowired
    private SessaoService sessaoService;

    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<Sessao> sessaos = sessaoService.getAll();
        if (sessaos != null)
            return ResponseEntity.ok(sessaos);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma Sessão Encontrada"));
    }
    @GetMapping("/status/{status}/{id}")
    public ResponseEntity<Object> getAllByStatus(@PathVariable(name = "status") String status, @PathVariable("id") Long id)
    {
        List<Sessao> sessaos = sessaoService.getAllByStatus(status, id);
        if (sessaos != null)
            return ResponseEntity.ok().body(sessaos);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Sessao Encontrada"));

    }
    @GetMapping("/pendentes/{id}")
    public ResponseEntity<Object> getAllPendenteByFisioterapeutaId(@PathVariable("id") Long id)
    {
        List<Sessao> sessaos = sessaoService.getAllPendenteByFisioterapeutaId(id);
        if (sessaos != null)
            return ResponseEntity.ok().body(sessaos);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Sessao Encontrada"));

    }
    @GetMapping("/getAllByResponsavelId/{id}")
    public ResponseEntity<Object> getByUsuarioId(@PathVariable("id") Long id)
    {
        List<Sessao> sessaos = sessaoService.getAllByUsuarioId(id);
        if (sessaos != null)
            return ResponseEntity.ok(sessaos);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma Sessão Encontrada"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id)
    {
        Sessao sessao = sessaoService.getById(id);
        if (sessao != null)
            return ResponseEntity.ok().body(sessao);
        else
            return ResponseEntity.badRequest().body(new Erro("Sessão Nao Encontrada"));
    }
    @GetMapping("/getAllByPaciente/{id}")
    public ResponseEntity<Object> getAllByPaciente(@PathVariable("id") Long id)
    {
        List<Sessao> sessaos = sessaoService.getAllByPaciente(id);
        if (sessaos.size() > 0)
            return ResponseEntity.ok().body(sessaos);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma Sessão Encontrada"));
    }
    @GetMapping("/play/{id}")
    public ResponseEntity<Object> getCodigosUnity(@PathVariable("id") Long id)
    {
        List<String> codigos = sessaoService.getCodigosUnity(id);
        if (codigos.size() > 0)
            return ResponseEntity.ok().body(codigos);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Código Encontrado"));
    }
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Sessao sessao)
    {
        Sessao novaSessao = sessaoService.save(sessao);
        if (novaSessao != null)
            return ResponseEntity.ok().body(novaSessao);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Sessão"));
    }
    @PostMapping("/resultado/{id}")
    public ResponseEntity<Object> addResultadoSessao(@RequestBody ResultadoSessao resultadoSessao, @PathVariable("id") Long id)
    {
        ResultadoSessao novoResultadoSessao = sessaoService.addResultadoSessao(resultadoSessao, id);
        if (novoResultadoSessao != null)
            return ResponseEntity.ok().body(novoResultadoSessao);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Resultado Sessão"));
    }
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Sessao sessao)
    {
        Sessao novaSessao = sessaoService.save(sessao);
        if (novaSessao != null)
            return ResponseEntity.ok().body(novaSessao);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Alterar Sessão"));

    }
    @PutMapping("aprovar/{id}")
    public ResponseEntity<Object> aprovarSessaoPendente(@RequestBody AprovacaoSessao aprovacaoSessao, @PathVariable("id") Long id)
    {
        AprovacaoSessao novaAprovacaoSessao = sessaoService.aprovarSessaoPendente(aprovacaoSessao, id);
        if (novaAprovacaoSessao != null)
            return ResponseEntity.ok().body(novaAprovacaoSessao);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Aprovar/Recusar Sessão"));
    }
    @PutMapping("cancelar/{id}")
    public ResponseEntity<Object> cancelarSessaoPendente(@PathVariable("id") Long id)
    {
        Sessao sessao = sessaoService.cancelarSessaoPendente(id);
        if (sessao != null)
            return ResponseEntity.ok().body(sessao);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Cancelar Sessão"));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id)
    {
        if (sessaoService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Sessão"));
    }
}
