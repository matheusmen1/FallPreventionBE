package com.unoeste.fallpreventionbe.restControllers;

import com.unoeste.fallpreventionbe.dto.ConcluirSessaoDTO;
import com.unoeste.fallpreventionbe.dto.RelatorioEscalaDTO;
import com.unoeste.fallpreventionbe.dto.RelatorioSessaoDTO;
import com.unoeste.fallpreventionbe.dto.SalvarEscalaDTO;
import com.unoeste.fallpreventionbe.entities.Erro;
import com.unoeste.fallpreventionbe.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/relatorios", "/apis/relatorios"})
@CrossOrigin
public class RelatorioRestController
{
    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/sessao/{ses_id}")
    public ResponseEntity<Object> buscarRelatorioSessao(@PathVariable("ses_id") Long sesId)
    {
        try {
            RelatorioSessaoDTO relatorio = relatorioService.buscarRelatorioPorSessao(sesId);
            if (relatorio != null) {
                return ResponseEntity.ok(relatorio);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Erro("Sessão ou relatório não encontrado para o ID informado."));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new Erro("Erro ao carregar o relatório da sessão: " + e.getMessage()));
        }
    }

    @PostMapping("/fase/{resf_id}/escala")
    public ResponseEntity<Object> salvarEscalaFase(
            @PathVariable("resf_id") Long resfId,
            @RequestBody SalvarEscalaDTO dto
    ) {
        try {
            RelatorioEscalaDTO escalaSalva = relatorioService.salvarEscalaFase(resfId, dto);
            if (escalaSalva != null) {
                return ResponseEntity.ok(escalaSalva);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Erro("Fase executada não encontrada para o ID: " + resfId));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new Erro(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new Erro("Erro ao salvar avaliação da Escala de Berg: " + e.getMessage()));
        }
    }

    @PostMapping("/sessao/{ses_id}/concluir")
    public ResponseEntity<Object> concluirSessao(
            @PathVariable("ses_id") Long sesId,
            @RequestBody(required = false) ConcluirSessaoDTO dto
    ) {
        try {
            RelatorioSessaoDTO relatorio = relatorioService.concluirSessao(sesId, dto);
            if (relatorio != null) {
                return ResponseEntity.ok(relatorio);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Erro("Sessão não encontrada."));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new Erro("Erro ao concluir a sessão: " + e.getMessage()));
        }
    }
}
