package com.unoeste.fallpreventionbe.dto;

import java.time.LocalDateTime;
import java.util.List;

public class RelatorioSessaoDTO
{
    private Long sessaoId;
    private Long resultadoId;
    private String sessaoStatus;
    private String pacienteNome;
    private String fisioterapeutaNome;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private Integer duracaoTotalMinutos;
    private String observacao;
    private List<RelatorioFaseDTO> fases;

    public RelatorioSessaoDTO() {
    }

    public RelatorioSessaoDTO(
            Long sessaoId,
            Long resultadoId,
            String sessaoStatus,
            String pacienteNome,
            String fisioterapeutaNome,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim,
            Integer duracaoTotalMinutos,
            String observacao,
            List<RelatorioFaseDTO> fases
    ) {
        this.sessaoId = sessaoId;
        this.resultadoId = resultadoId;
        this.sessaoStatus = sessaoStatus;
        this.pacienteNome = pacienteNome;
        this.fisioterapeutaNome = fisioterapeutaNome;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.duracaoTotalMinutos = duracaoTotalMinutos;
        this.observacao = observacao;
        this.fases = fases;
    }

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }

    public Long getResultadoId() {
        return resultadoId;
    }

    public void setResultadoId(Long resultadoId) {
        this.resultadoId = resultadoId;
    }

    public String getSessaoStatus() {
        return sessaoStatus;
    }

    public void setSessaoStatus(String sessaoStatus) {
        this.sessaoStatus = sessaoStatus;
    }

    public String getPacienteNome() {
        return pacienteNome;
    }

    public void setPacienteNome(String pacienteNome) {
        this.pacienteNome = pacienteNome;
    }

    public String getFisioterapeutaNome() {
        return fisioterapeutaNome;
    }

    public void setFisioterapeutaNome(String fisioterapeutaNome) {
        this.fisioterapeutaNome = fisioterapeutaNome;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<RelatorioFaseDTO> getFases() {
        return fases;
    }

    public void setFases(List<RelatorioFaseDTO> fases) {
        this.fases = fases;
    }
}
