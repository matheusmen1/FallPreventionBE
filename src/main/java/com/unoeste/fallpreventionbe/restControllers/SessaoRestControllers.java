package com.unoeste.fallpreventionbe.restControllers;

import com.unoeste.fallpreventionbe.entities.*;
import com.unoeste.fallpreventionbe.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/apis/sessao")
@CrossOrigin
public class SessaoRestControllers
{
    @Autowired
    private SessaoService sessaoService;

    @PutMapping("/iniciar/{id}")
    public ResponseEntity<Object> iniciarSessao(@PathVariable Long id)
    {
        if (sessaoService.iniciarSessao(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Iniciar Sessão"));

    }
    @PutMapping("/mensagem/{msg}")
    public ResponseEntity<Object> enviarMensagem(@PathVariable("msg") String msg)
    {
        if (sessaoService.enviarMensagem(msg))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Enviar Mensagem"));
    }
    @PutMapping("/proxima/{id}")
    public ResponseEntity<Object> proximaFase(@PathVariable("id") Long id)
    {
        if (sessaoService.proximaFase(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Pular Fase"));
    }
    @PutMapping("/pausar/{id}")
    public ResponseEntity<Object> pausarFase(@PathVariable("id") Long id)
    {
        if (sessaoService.pausarFase(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Pausar Fase"));
    }
    @PutMapping("/retomar/{id}")
    public ResponseEntity<Object> retomarFase(@PathVariable("id") Long id)
    {
        if (sessaoService.retomarFase(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Retomar Fase"));
    }
    @PutMapping("/finalizar/{id}")
    public ResponseEntity<Object> finalizarSessao(@PathVariable("id") Long id)
    {
        if (sessaoService.finalizarSessao(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Finalizar Sessao"));
    }
    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<Sessao> sessaos = sessaoService.getAll();
        if (sessaos != null)
            return ResponseEntity.ok(sessaos);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma Sessão Encontrada"));
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<Object> getAllByStatus(@PathVariable("status") String status)
    {
        List<Sessao> sessaos = sessaoService.getAllByStatus(status);
        if (sessaos != null)
            return ResponseEntity.ok(sessaos);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma Sessão Encontrada"));
    }
    @GetMapping("/status/{status}/{id}")
    public ResponseEntity<Object> getAllByStatusById(@PathVariable(name = "status") String status, @PathVariable("id") Long id)
    {
        List<Sessao> sessaos = sessaoService.getAllByStatusById(status, id);
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
        if (sessaos != null)
            return ResponseEntity.ok().body(sessaos);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma Sessão Encontrada"));
    }
    @GetMapping("/getAllObservacoesByPacienteAndSessao/{idSessao}/{idPaciente}")
    public ResponseEntity<Object> getAllObservacoesByPacienteAndSessao(@PathVariable("idSessao") Long idSessao, @PathVariable("idPaciente") Long idPaciente)
    {
        List<SessaoObservacao> sessaoObservacaos = sessaoService.getAllObservacoesByPacienteAndSessao(idSessao, idPaciente);
        if (sessaoObservacaos != null)
            return ResponseEntity.ok(sessaoObservacaos);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma Observação Encontrada"));
    }
    @GetMapping("/gravacoes/{idSessao}/{idPaciente}")
    public ResponseEntity<Object> getAllGravacoesByPacienteAndSessao(@PathVariable("idSessao") Long idSessao, @PathVariable("idPaciente") Long idPaciente)
    {
        List<SessaoGravacao> sessaoGravacaos = sessaoService.getAllGravacoesByPacienteAndSessao(idSessao, idPaciente);
        if (sessaoGravacaos != null)
            return ResponseEntity.ok(sessaoGravacaos);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma Gravação Encontrada"));
    }
    @GetMapping("/gravacao/play/{id}")
    public ResponseEntity<Object> playGravacao(@PathVariable("id") Long id)
    {
        Resource resource = sessaoService.playGravacao(id);
        if (resource != null)
            return ResponseEntity.ok().contentType(MediaType.parseMediaType("video/webm"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao dar Play na Gravação"));

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
    @PostMapping("/observacao/{id}")
    public ResponseEntity<Object> addObservacao(@RequestBody SessaoObservacao sessaoObservacao, @PathVariable("id") Long id)
    {
        SessaoObservacao novaSessaoObservacao = sessaoService.addObservacao(sessaoObservacao, id);
        if (novaSessaoObservacao != null)
            return ResponseEntity.ok(novaSessaoObservacao);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Observação"));
    }
    @PostMapping("/gravacao/{id}")
    public ResponseEntity<Object> addGravacao(@PathVariable("id") Long id, @RequestParam("video")MultipartFile arquivoVideo)
    {
        if (sessaoService.addGravacao(id, arquivoVideo))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Video"));
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
    @PutMapping("sairSala/{id}")
    public ResponseEntity<Object> sairSala(@PathVariable("id") Long id)
    {
        Sessao sessao = sessaoService.sairSala(id);
        if (sessao != null)
            return ResponseEntity.ok().body(sessao);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro Ao Sair da Sala"));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id)
    {
        if (sessaoService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Sessão"));
    }
    @DeleteMapping("/observacao/{id}")
    public ResponseEntity<Object> deleteObservacao(@PathVariable("id") Long id)
    {
        if (sessaoService.deleteObservacao(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Observação"));
    }
    @DeleteMapping("/gravacao/{id}")
    public ResponseEntity<Object> deleteGravacao(@PathVariable("id") Long id)
    {
        if (sessaoService.deleteGravacao(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Gravação"));
    }

}
