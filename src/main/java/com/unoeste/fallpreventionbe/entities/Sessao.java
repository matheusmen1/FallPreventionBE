package com.unoeste.fallpreventionbe.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sessao")
public class Sessao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ses_id")
    private Long id;
    @Column(name = "ses_data_hora")
    private LocalDateTime data_hora;
    @Column(name = "ses_ordem_atual")
    private Integer ordemAtual;
    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usuario responsavel;
    @ManyToOne
    @JoinColumn(name = "pac_id")
    private Paciente paciente;
    @Column(name = "ses_status")
    private String status; // concluida, pendente, cancelada

    @OneToOne(mappedBy = "sessao") // cascade = CascadeType.ALL
    private AprovacaoSessao aprovacaoSessao;

    @OneToOne(mappedBy = "sessao") // cascade = CascadeType.ALL
    private ResultadoSessao resultadoSessao;

    @OneToMany (mappedBy = "sessao") // cascade = CascadeType.ALL
    private List<SessaoFase> sessaoFases;

    @OneToMany(mappedBy = "sessao")
    private List<SessaoObservacao> sessaoObservacaos;

    @OneToMany(mappedBy = "sessao")
    private List<SessaoGravacao> sessaoGravacaos;

    public Sessao(Long id, LocalDateTime data_hora, Integer ordemAtual, Usuario responsavel, Paciente paciente, String status) {
        this.id = id;
        this.data_hora = data_hora;
        this.ordemAtual = ordemAtual;
        this.responsavel = responsavel;
        this.paciente = paciente;
        this.status = status;
    }

    public Sessao()
    {
        this(0L, null, null, null, null, "");
    }

    public Integer getOrdemAtual() {
        return ordemAtual;
    }

    public void setOrdemAtual(Integer ordemAtual) {
        this.ordemAtual = ordemAtual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AprovacaoSessao getAprovacaoSessao() {
        return aprovacaoSessao;
    }

    public void setAprovacaoSessao(AprovacaoSessao aprovacaoSessao) {
        this.aprovacaoSessao = aprovacaoSessao;
    }

    public ResultadoSessao getResultadoSessao() {
        return resultadoSessao;
    }

    public void setResultadoSessao(ResultadoSessao resultadoSessao) {
        this.resultadoSessao = resultadoSessao;
    }

    public List<SessaoFase> getSessaoFases() {
        return sessaoFases;
    }

    public void setSessaoFases(List<SessaoFase> sessaoFases) {
        this.sessaoFases = sessaoFases;
    }

    public List<SessaoObservacao> getSessaoObservacaos() {
        return sessaoObservacaos;
    }

    public void setSessaoObservacaos(List<SessaoObservacao> sessaoObservacaos) {
        this.sessaoObservacaos = sessaoObservacaos;
    }

    public List<SessaoGravacao> getSessaoGravacaos() {
        return sessaoGravacaos;
    }

    public void setSessaoGravacaos(List<SessaoGravacao> sessaoGravacaos) {
        this.sessaoGravacaos = sessaoGravacaos;
    }
}
