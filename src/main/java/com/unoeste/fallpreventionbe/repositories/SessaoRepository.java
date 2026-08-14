package com.unoeste.fallpreventionbe.repositories;

import com.unoeste.fallpreventionbe.entities.Sessao;
import com.unoeste.fallpreventionbe.entities.SessaoGravacao;
import com.unoeste.fallpreventionbe.entities.SessaoObservacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
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

    @Query(value = "SELECT * FROM sessao WHERE ses_status = :status AND usr_id = :id", nativeQuery = true)
    public List<Sessao> getAllByStatusById(String status, Long id);

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

    @Query(value = "SELECT sessao.* FROM sessao INNER JOIN usuario ON usuario.usr_id = sessao.usr_id  WHERE (usuario.usr_responsavel_id = :id OR usuario.usr_id = :id) AND sessao.ses_status = 'PENDENTE' ", nativeQuery = true)
    public List<Sessao> getAllPendenteByFisioterapeutaId(Long id);

    @Query(value = "SELECT * FROM sessao WHERE ses_status = :status ORDER BY ses_data_hora ASC ", nativeQuery = true)
    public List<Sessao> getAllByStatus(@Param("status") String status);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sessao_observacao (seso_observacao, seso_data_hora, sesf_id, ses_id) VALUES (:observacao, :data_hora, :sesf_id, :ses_id)", nativeQuery = true)
    public void addObservacao(@Param("observacao") String observacao,@Param("data_hora") LocalDateTime data_hora,@Param("sesf_id") Long sesf_id, @Param("ses_id") Long ses_id);

    @Query(value = "SELECT sessao_observacao.* FROM sessao_observacao INNER JOIN sessao ON sessao.ses_id = sessao_observacao.ses_id WHERE sessao.pac_id = :idPaciente AND sessao.ses_id = :idSessao", nativeQuery = true)
    public List<SessaoObservacao> getAllObservacoesByPacienteAndSessao(@Param("idSessao") Long idSessao,@Param("idPaciente") Long idPaciente);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sessao_observacao WHERE ses_id = :id", nativeQuery = true)
    public void deleteSessaoObservacaoAll(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sessao_gravacao WHERE ses_id = :id", nativeQuery = true)
    public void deleteSessaoGravacaooAll(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sessao_observacao WHERE seso_id = :id", nativeQuery = true)
    public void deleteSessaoObservacao(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sessao_gravacao WHERE sesg_id = :id", nativeQuery = true)
    public void deleteSessaoGravacao(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sessao_gravacao (sesg_caminho_arquivo, sesg_data_hora, ses_id) VALUES (:caminho, :data_hora, :id)", nativeQuery = true)
    void addGravacao(@Param("caminho") String caminho,@Param("data_hora") LocalDateTime dataHora,@Param("id") Long id);

    @Query(value = "SELECT sessao_gravacao.* FROM sessao_gravacao INNER JOIN sessao ON sessao.ses_id = sessao_gravacao.ses_id WHERE sessao.pac_id = :idPaciente AND sessao.ses_id = :idSessao", nativeQuery = true)
    public List<SessaoGravacao> getAllGravacoesByPacienteAndSessao(@Param("idSessao") Long idSessao,@Param("idPaciente") Long idPaciente);

    @Query(value = "SELECT * FROM sessao_gravacao WHERE sessao_gravacao.sesg_id = :id", nativeQuery = true)
    public SessaoGravacao getSessaoGravacaoById(@Param("id") Long id);


}
