package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.TipoExercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoExercicioRepository extends JpaRepository<TipoExercicio, Long>
{
    @Query(value = "SELECT * FROM tipo_exercicio WHERE tpe_nome ILIKE :nome", nativeQuery = true)
    public List<TipoExercicio> getAllByName(@Param("nome") String nome);
}
