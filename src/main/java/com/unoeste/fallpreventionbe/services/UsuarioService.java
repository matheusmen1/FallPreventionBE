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
            else
            {
                Usuario aux = usuarioRepository.findById(usuario.getId()).orElse(null);
                if (aux != null)
                {
                    if (aux.getNivel() > 0 && usuario.getNivel() < 0)
                    {
                        List<Usuario> monitores = usuarioRepository.getMonitoresByResponsavel(aux.getId());
                        if (monitores.size() > 0)
                            return null;
                    }
                }
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
            if (usuario.getId() != null)
            {
                Usuario aux = usuarioRepository.getUsuarioByEmailOrCpf(usuario.getEmail(), usuario.getCpf());
                if (aux != null && !aux.getId().equals(usuario.getId()))
                    return null;
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
                // preciso verificar, se há ao menos um usu adm.
                boolean flag = true;
                if (aux.getNivel() > 1) // se corresponde ao do adm, preciso verificar se há ao menos um outro, sem ser ele
                {
                    flag = false;
                    List<Usuario> usuarios = usuarioRepository.findAll();

                    for (int i = 0; i < usuarios.size() && !flag; i++)
                    {
                        if (usuarios.get(i) != aux && usuarios.get(i).getNivel() > 1) // 0 - Monitor 1 - Fisio 2 - Adm
                            flag = true;
                    }
                }
                if (flag)
                {
                    usuarioRepository.delete(aux);
                    return true;
                }
                return false;
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

    public List<Usuario> getAllMonitoresAutorizados(Long id)
    {
        return usuarioRepository.getAllMonitoresAutorizados(id);
    }
}
