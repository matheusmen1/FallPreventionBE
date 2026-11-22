package com.unoeste.fallpreventionbe.restControllers;

import com.unoeste.fallpreventionbe.entities.Configuracao;
import com.unoeste.fallpreventionbe.entities.Erro;
import com.unoeste.fallpreventionbe.services.ConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/apis/configuracao")
public class ConfiguracaoRestController {

    @Autowired
    private ConfiguracaoService configuracaoService;

    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<Configuracao> configuracaos = configuracaoService.getAll();
        if (configuracaos != null)
            return ResponseEntity.ok().body(configuracaos);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma Configuração Encontrada"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id)
    {
        Configuracao configuracao = configuracaoService.getById(id);
        if (configuracao != null)
            return ResponseEntity.ok().body(configuracao);
        else
            return ResponseEntity.badRequest().body(new Erro("Configuração Não Encontrada"));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Configuracao configuracao)
    {
        Configuracao novaConfiguracao = configuracaoService.save(configuracao);
        if (novaConfiguracao != null)
            return ResponseEntity.ok().body(novaConfiguracao);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Gravar Configuração"));
    }
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Configuracao configuracao)
    {
        Configuracao novaConfiguracao = configuracaoService.save(configuracao);
        if (novaConfiguracao != null)
            return ResponseEntity.ok().body(novaConfiguracao);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Alterar Configuração"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id)
    {
        if (configuracaoService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Configuração"));
    }
    @GetMapping("/getIPv4Local")
    public ResponseEntity<Object> getIpV4Local()
    {
        Configuracao configuracao = configuracaoService.AtualizarIPv4Local();
       if (configuracao != null)
           return ResponseEntity.ok().body(configuracao);
       else
           return ResponseEntity.badRequest().body(new Erro("Erro ao Obter IPv4 Local"));
    }


}
