package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{

    @Query(value = "SELECT * FROM usuario WHERE usr_login = :login", nativeQuery = true)
    public Usuario getUsuarioByLogin(@Param("login") String login);
}
