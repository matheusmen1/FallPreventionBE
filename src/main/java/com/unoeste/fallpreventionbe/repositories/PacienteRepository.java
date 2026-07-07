package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query(value = "SELECT * FROM paciente WHERE pac_cpf = :cpf OR pac_email = :email", nativeQuery = true)
    public Paciente getByCpfOrEmail(@Param("cpf") String cpf, @Param("email") String email);

    @Query(value = "SELECT * FROM paciente WHERE pac_cpf =:cpf", nativeQuery = true)
    public Paciente getByEmail(@Param("cpf") String cpf);

    @Query(value = "SELECT * FROM paciente WHERE pac_nome ILIKE :nome", nativeQuery = true)
    public Paciente getByNome(@Param("nome") String nome);
}
