package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.TipoExercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoExercicioRepository extends JpaRepository<TipoExercicio, Long>
{

}
