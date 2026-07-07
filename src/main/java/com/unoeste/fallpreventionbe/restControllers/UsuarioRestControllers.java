package com.unoeste.fallpreventionbe.restControllers;

import com.unoeste.fallpreventionbe.entities.Erro;
import com.unoeste.fallpreventionbe.entities.Usuario;
import com.unoeste.fallpreventionbe.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apis/usuario")
@CrossOrigin
public class UsuarioRestControllers
{
    @Autowired
    private UsuarioService usuarioService;

    // CRUD
    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<Usuario> usuarios = usuarioService.getAll();
        if (usuarios.size() > 0)
            return ResponseEntity.ok().body(usuarios);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Usuário Cadastrado"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable("id") Long id)
    {
        Usuario usuario = usuarioService.getById(id);
        if (usuario != null)
            return ResponseEntity.ok().body(usuario);
        else
            return ResponseEntity.badRequest().body(new Erro("Usuario Nao Encontrado"));
    }
    @GetMapping("/getMonitoresByResponsavel/{idResponsavel}")
    public ResponseEntity<Object> getMonitoresByResponsavel(@PathVariable("idResponsavel") Long id)
    {
        List<Usuario> usuarios = usuarioService.getMonitoresByResponsavel(id);
        if (usuarios.size() > 0)
            return ResponseEntity.ok().body(usuarios);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum Monitor Encontrado"));
    }
    @PostMapping
    public ResponseEntity<Object> addUsuario(@RequestBody Usuario usuario)
    {
        Usuario novoUsuario = usuarioService.save(usuario);
        if (novoUsuario != null)
            return ResponseEntity.ok().body(novoUsuario);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Cadastrar Novo Usuário"));
    }
    @PostMapping("/logar")
    public ResponseEntity<Object> logar(@RequestParam String email, @RequestParam String senha)
    {
        Usuario usuario = usuarioService.logar(email, senha);
        if (usuario != null)
            return ResponseEntity.ok().body(usuario);
        else
            return ResponseEntity.badRequest().body(new Erro("Email E/Ou Senha Incorreto(s)"));
    }
    @PutMapping
    public ResponseEntity<Object> updateUsuario(@RequestBody Usuario usuario)
    {
        Usuario novoUsuario = usuarioService.save(usuario);
        if (novoUsuario != null)
            return ResponseEntity.ok(novoUsuario);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Alterar Usuário"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable("id") Long id)
    {
        if (usuarioService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao Apagar Usuário"));
    }

}
