package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{

    @Query(value = "SELECT * FROM usuario WHERE usr_email = :email OR usr_cpf =:cpf", nativeQuery = true)
    public Usuario getUsuarioByEmailOrCpf(@Param("email") String email, @Param("cpf") String cpf);

    @Query(value = "SELECT * FROM usuario WHERE usr_responsavel_id = :id", nativeQuery = true)
    public List<Usuario> getMonitoresByResponsavel(Long id);

    @Query(value = "SELECT * FROM usuario WHERE usr_nivel >= 1", nativeQuery = true)
    public List<Usuario> getAllFisioterapeutas();

    @Query(value = "SELECT usuario.* FROM usuario INNER JOIN sessao_monitores_autorizados ON sessao_monitores_autorizados.usr_id = usuario.usr_id WHERE sessao_monitores_autorizados.ses_id =:id", nativeQuery = true)
    List<Usuario> getAllMonitoresAutorizados(Long id);
}
