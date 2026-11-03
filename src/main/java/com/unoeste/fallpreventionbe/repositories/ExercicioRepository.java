package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long>
{
    @Query(value = "SELECT * FROM exercicio WHERE exe_nome ILIKE :nome", nativeQuery = true)
    public List<Exercicio> getAllByName(@Param("nome") String nome);

    @Query(value = "SELECT * FROM exercicio WHERE COALESCE(exe_dificuldade, 1) <= " +
            "CASE " +
            "  WHEN :pontuacao <= 16 THEN 4 " +
            "  WHEN :pontuacao BETWEEN 17 AND 19 THEN 3 " +
            "  WHEN :pontuacao BETWEEN 20 AND 27 THEN 2 " +
            "  ELSE 1 " +
            "END", nativeQuery = true)
    public List<Exercicio> getAllByPontuacao(@Param("pontuacao") Integer pontuacao);

    @Query(value = "SELECT * FROM exercicio WHERE tpe_id = :tpeId AND COALESCE(exe_dificuldade, 1) <= " +
            "CASE " +
            "  WHEN :pontuacao <= 16 THEN 4 " +
            "  WHEN :pontuacao BETWEEN 17 AND 19 THEN 3 " +
            "  WHEN :pontuacao BETWEEN 20 AND 27 THEN 2 " +
            "  ELSE 1 " +
            "END", nativeQuery = true)
    public List<Exercicio> getAllByPontuacaoAndTipo(@Param("pontuacao") Integer pontuacao, @Param("tpeId") Long tpeId);
}
