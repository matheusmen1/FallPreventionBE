package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.Doenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoencaRepository extends JpaRepository<Doenca, Long> {

    @Query(value = "SELECT * FROM doenca WHERE doe_nome ILIKE :nome", nativeQuery = true)
    List<Doenca> getAllByName(@Param("nome") String nome);
}
