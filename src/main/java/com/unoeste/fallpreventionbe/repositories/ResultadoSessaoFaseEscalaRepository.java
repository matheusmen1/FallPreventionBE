package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.ResultadoSessaoFaseEscala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultadoSessaoFaseEscalaRepository extends JpaRepository<ResultadoSessaoFaseEscala, Long>
{
    Optional<ResultadoSessaoFaseEscala> findByResultadoSessaoFase_Id(Long resultadoFaseId);
}
