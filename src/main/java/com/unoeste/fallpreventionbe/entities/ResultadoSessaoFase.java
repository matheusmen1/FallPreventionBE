package com.unoeste.fallpreventionbe.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resultado_sessao_fase")
public class ResultadoSessaoFase
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resf_id")
    private Long id;

    @Column(name = "resf_repeticoes_realizadas")
    private Integer repeticoesRealizadas;

    @Column(name = "resf_tempo_realizado")
    private Float tempoRealizado;

    @Column(name = "resf_data_hora_inicio", nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(name = "resf_data_hora_fim")
    private LocalDateTime dataHoraFim;

    @ManyToOne
    @JoinColumn(name = "sesf_id", nullable = false)
    private SessaoFase sessaoFase;

    @OneToOne(mappedBy = "resultadoSessaoFase")
    private ResultadoSessaoFaseEscala escala;

    public ResultadoSessaoFase(
            Long id,
            Integer repeticoesRealizadas,
            Float tempoRealizado,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim,
            SessaoFase sessaoFase,
            ResultadoSessaoFaseEscala escala
    ) {
        this.id = id;
        this.repeticoesRealizadas = repeticoesRealizadas;
        this.tempoRealizado = tempoRealizado;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.sessaoFase = sessaoFase;
        this.escala = escala;
    }

    public ResultadoSessaoFase() {
        this(null, 0, 0f, LocalDateTime.now(), null, null, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRepeticoesRealizadas() {
        return repeticoesRealizadas;
    }

    public void setRepeticoesRealizadas(Integer repeticoesRealizadas) {
        this.repeticoesRealizadas = repeticoesRealizadas;
    }

    public Float getTempoRealizado() {
        return tempoRealizado;
    }

    public void setTempoRealizado(Float tempoRealizado) {
        this.tempoRealizado = tempoRealizado;
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

    public SessaoFase getSessaoFase() {
        return sessaoFase;
    }

    public void setSessaoFase(SessaoFase sessaoFase) {
        this.sessaoFase = sessaoFase;
    }

    public ResultadoSessaoFaseEscala getEscala() {
        return escala;
    }

    public void setEscala(ResultadoSessaoFaseEscala escala) {
        this.escala = escala;
    }
}
