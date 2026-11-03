package com.unoeste.fallpreventionbe.dto;

import java.time.LocalDateTime;

public class RelatorioFaseDTO
{
    private Long resultadoFaseId;
    private Long sessaoFaseId;
    private Integer ordem;
    private String exercicioNome;
    private String exercicioDescricao;
    private Integer metaRepeticoes;
    private Float metaTempo;
    private Boolean isRepeticao;
    private Integer repeticoesRealizadas;
    private Float tempoRealizado;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String observacaoFase;
    private RelatorioEscalaDTO escala;

    public RelatorioFaseDTO() {
    }

    public RelatorioFaseDTO(
            Long resultadoFaseId,
            Long sessaoFaseId,
            Integer ordem,
            String exercicioNome,
            String exercicioDescricao,
            Integer metaRepeticoes,
            Float metaTempo,
            Boolean isRepeticao,
            Integer repeticoesRealizadas,
            Float tempoRealizado,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim,
            String observacaoFase,
            RelatorioEscalaDTO escala
    ) {
        this.resultadoFaseId = resultadoFaseId;
        this.sessaoFaseId = sessaoFaseId;
        this.ordem = ordem;
        this.exercicioNome = exercicioNome;
        this.exercicioDescricao = exercicioDescricao;
        this.metaRepeticoes = metaRepeticoes;
        this.metaTempo = metaTempo;
        this.isRepeticao = isRepeticao;
        this.repeticoesRealizadas = repeticoesRealizadas;
        this.tempoRealizado = tempoRealizado;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.observacaoFase = observacaoFase;
        this.escala = escala;
    }

    public Long getResultadoFaseId() {
        return resultadoFaseId;
    }

    public void setResultadoFaseId(Long resultadoFaseId) {
        this.resultadoFaseId = resultadoFaseId;
    }

    public Long getSessaoFaseId() {
        return sessaoFaseId;
    }

    public void setSessaoFaseId(Long sessaoFaseId) {
        this.sessaoFaseId = sessaoFaseId;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public String getExercicioNome() {
        return exercicioNome;
    }

    public void setExercicioNome(String exercicioNome) {
        this.exercicioNome = exercicioNome;
    }

    public String getExercicioDescricao() {
        return exercicioDescricao;
    }

    public void setExercicioDescricao(String exercicioDescricao) {
        this.exercicioDescricao = exercicioDescricao;
    }

    public Integer getMetaRepeticoes() {
        return metaRepeticoes;
    }

    public void setMetaRepeticoes(Integer metaRepeticoes) {
        this.metaRepeticoes = metaRepeticoes;
    }

    public Float getMetaTempo() {
        return metaTempo;
    }

    public void setMetaTempo(Float metaTempo) {
        this.metaTempo = metaTempo;
    }

    public Boolean getIsRepeticao() {
        return isRepeticao;
    }

    public void setIsRepeticao(Boolean isRepeticao) {
        this.isRepeticao = isRepeticao;
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

    public String getObservacaoFase() {
        return observacaoFase;
    }

    public void setObservacaoFase(String observacaoFase) {
        this.observacaoFase = observacaoFase;
    }

    public RelatorioEscalaDTO getEscala() {
        return escala;
    }

    public void setEscala(RelatorioEscalaDTO escala) {
        this.escala = escala;
    }
}
