package com.unoeste.fallpreventionbe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessao_observacao")
public class SessaoObservacao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seso_id")
    private Long id;
    @Column(name = "seso_observacao")
    private String observacao;
    @Column(name = "seso_data_hora")
    private LocalDateTime data_hora;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ses_id")
    private Sessao sessao;

    @ManyToOne
    @JoinColumn(name = "sesf_id")
    private SessaoFase sessaoFase;

    public SessaoObservacao(Long id, String observacao, LocalDateTime data_hora, Sessao sessao, SessaoFase sessaoFase)
    {
        this.id = id;
        this.observacao = observacao;
        this.data_hora = data_hora;
        this.sessao = sessao;
        this.sessaoFase = sessaoFase;
    }

    public SessaoObservacao()
    {
        this(0L, "", null, null, null);
    }

    public SessaoFase getSessaoFase() {
        return sessaoFase;
    }

    public void setSessaoFase(SessaoFase sessaoFase) {
        this.sessaoFase = sessaoFase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
}
