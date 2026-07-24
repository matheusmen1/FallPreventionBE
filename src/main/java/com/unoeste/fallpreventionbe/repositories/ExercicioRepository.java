package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long>
{

}
