package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.PreAvaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface PreAvaliacaoRepository extends JpaRepository<PreAvaliacao, Long>
{
    @Query(value = "SELECT * FROM pre_avaliacao WHERE pac_id = :id", nativeQuery = true)
    public List<PreAvaliacao> getAllByPaciente(@RequestParam("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO metrica (met_nome_teste, met_pontuacao, pre_id) VALUES (:nome_teste, :pontuacao, :pre_id)", nativeQuery = true)
    public void addMetrica(@Param("nome_teste") String nome_teste, @Param("pontuacao") double pontuacao, @Param("pre_id") Long pre_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM metrica WHERE pre_id = :id", nativeQuery = true)
    public void deleteMetrica(@Param("id") Long id);
}
