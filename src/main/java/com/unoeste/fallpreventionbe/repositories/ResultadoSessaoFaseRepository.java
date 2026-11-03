package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.ResultadoSessaoFase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultadoSessaoFaseRepository extends JpaRepository<ResultadoSessaoFase, Long>
{
    List<ResultadoSessaoFase> findBySessaoFase_Sessao_IdOrderBySessaoFase_OrdemAsc(Long sessaoId);
    Optional<ResultadoSessaoFase> findFirstBySessaoFase_IdOrderByIdDesc(Long sessaoFaseId);
}
