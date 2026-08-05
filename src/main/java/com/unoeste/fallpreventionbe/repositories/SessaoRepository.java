package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long>
{
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sessao_fase (sesf_ordem, ses_id, exe_id) VALUES (:ordem, :ses_id, :exe_id)", nativeQuery = true)
    public void addSessaoFase(@Param("ordem") int ordem, @Param("ses_id") Long ses_id, @Param("exe_id") Long exe_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sessao_fase WHERE ses_id = :id", nativeQuery = true)
    public void deleteSessaoFase(@Param("id") Long id);

    @Query(value = "SELECT * FROM sessao WHERE ses_status =:status", nativeQuery = true)
    public List<Sessao> getAllByStatus(String status);

    @Query(value = "SELECT * FROM sessao WHERE pac_id = :id", nativeQuery = true)
    public List<Sessao> getAllByPaciente(Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO aprovacao_sessao (apr_data_hora, apr_motivo, apr_status, ses_id, usr_id) VALUES (:dataHora, :motivo, :status, :idSessao, :idFisio)", nativeQuery = true)
    public void addAprovacaoSessao(@Param("dataHora") LocalDateTime dataHora,@Param("idFisio") Long idFisio,@Param("status") String status, @Param("motivo") String motivo,@Param("idSessao") Long idSessao);

    @Modifying
    @Transactional
    @Query(value = "UPDATE aprovacao_sessao SET apr_data_hora = :dataHora,  apr_motivo = :motivo, apr_status = :status, usr_id = :idFisio WHERE ses_id = :idSessao", nativeQuery = true)
    public void updateAprovacaoSessao(@Param("dataHora") LocalDateTime dataHora,@Param("idFisio") Long idFisio,@Param("status") String status, @Param("motivo") String motivo,@Param("idSessao") Long idSessao);

    @Query(value = "SELECT exe_codigo_nome FROM exercicio INNER JOIN sessao_fase ON exercicio.exe_id = sessao_fase.exe_id AND sessao_fase.ses_id = :id ORDER BY exe_codigo_nome DESC", nativeQuery = true)
    public List<String> getCodigoUnity(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO resultado_sessao (res_duracao, res_observacao, ses_id) VALUES (:duracao, :observacao, :id)", nativeQuery = true)
    public void addResultadoSessao(Integer duracao, String observacao, Long id);

    @Query(value = "SELECT * FROM sessao WHERE usr_id = :id ", nativeQuery = true)
    public List<Sessao> getAllUsuarioById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM resultado_sessao WHERE ses_id = :id", nativeQuery = true)
    public void deleteResultadoSessao(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM aprovacao_sessao WHERE ses_id = :id", nativeQuery = true)
    public void deleteAprovacaoSessao(@Param("id") Long id);

}
