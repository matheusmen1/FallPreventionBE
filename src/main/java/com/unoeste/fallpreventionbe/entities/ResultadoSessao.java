package com.unoeste.fallpreventionbe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resultado_sessao")
public class ResultadoSessao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private Long id;

    @Column(name = "res_data_hora_inicio", nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(name = "res_data_hora_fim")
    private LocalDateTime dataHoraFim;

    @Column(name = "res_duracao_total_minutos")
    private Integer duracaoTotalMinutos;

    @Column(name = "res_observacao", columnDefinition = "TEXT")
    private String observacao;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "ses_id", nullable = false, unique = true)
    private Sessao sessao;

    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usuario usuarioExecutor;

    public ResultadoSessao(
            Long id,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim,
            Integer duracaoTotalMinutos,
            String observacao,
            Sessao sessao,
            Usuario usuarioExecutor
    ) {
        this.id = id;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.duracaoTotalMinutos = duracaoTotalMinutos;
        this.observacao = observacao;
        this.sessao = sessao;
        this.usuarioExecutor = usuarioExecutor;
    }

    public ResultadoSessao(Long id, Sessao sessao, Integer duracao, String observacao) {
        this(id, LocalDateTime.now(), LocalDateTime.now(), duracao, observacao, sessao, null);
    }

    public ResultadoSessao() {
        this(null, LocalDateTime.now(), null, 0, "", null, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public Integer getDuracaoTotalMinutos() {
        return duracaoTotalMinutos;
    }

    public void setDuracaoTotalMinutos(Integer duracaoTotalMinutos) {
        this.duracaoTotalMinutos = duracaoTotalMinutos;
    }

    public Integer getDuracao() {
        return duracaoTotalMinutos;
    }

    public void setDuracao(Integer duracao) {
        this.duracaoTotalMinutos = duracao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Usuario getUsuarioExecutor() {
        return usuarioExecutor;
    }

    public void setUsuarioExecutor(Usuario usuarioExecutor) {
        this.usuarioExecutor = usuarioExecutor;
    }
}
