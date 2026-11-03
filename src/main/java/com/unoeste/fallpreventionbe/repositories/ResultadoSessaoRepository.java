package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.ResultadoSessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultadoSessaoRepository extends JpaRepository<ResultadoSessao, Long>
{
    Optional<ResultadoSessao> findBySessaoId(Long sessaoId);
}
