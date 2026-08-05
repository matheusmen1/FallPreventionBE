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
}
