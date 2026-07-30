package com.unoeste.fallpreventionbe.services;

import com.unoeste.fallpreventionbe.entities.Usuario;
import com.unoeste.fallpreventionbe.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService
{
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll()
    {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public Usuario save(Usuario usuario)
    {
        try
        {
            if (usuario.getId() == null)
            {
                Usuario aux = usuarioRepository.getUsuarioByEmailOrCpf(usuario.getEmail(), usuario.getCpf());
                if (aux != null)
                    return null;
            }
            if (usuario.getNivel() < 1)
            {
                if (usuario.getResponsavel() == null)
                    return null;
                else
                {
                    Usuario responsavel = getById(usuario.getResponsavel().getId());
                    if (responsavel.getNivel() < 1)
                        return null;
                }
            }
            return usuarioRepository.save(usuario);

        }catch (Exception e)
        {
            return null;
        }
    }
    public boolean delete(Long id)
    {
        try
        {
            Usuario aux = usuarioRepository.findById(id).orElse(null);
            if (aux != null)
            {
                usuarioRepository.delete(aux);
                return true;
            }
            else
                return false;

        }catch (Exception e)
        {
            return false;
        }
    }

    public Usuario getById(Long id)
    {
        try {
            Usuario aux = usuarioRepository.findById(id).orElse(null);
            if (aux != null)
                return aux;
            else
                return null;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public Usuario logar(String email, String senha)
    {
        try
        {
            Usuario usuario = usuarioRepository.getUsuarioByEmailOrCpf(email, "");
            if (usuario != null && usuario.getSenha().equals(senha))
                return usuario;
            else
                return null;

        }catch (Exception e)
        {
            return null;
        }
    }

    public List<Usuario> getMonitoresByResponsavel(Long id)
    {
        return usuarioRepository.getMonitoresByResponsavel(id);
    }

    public List<Usuario> getAllFisioterapeutas()
    {
        return usuarioRepository.getAllFisioterapeutas();
    }
}
