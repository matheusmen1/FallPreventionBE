package com.unoeste.fallpreventionbe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "resultado_sessao")
public class ResultadoSessao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "ses_id")
    private Sessao sessao;

    @Column(name = "res_duracao")
    private Integer duracao;
    @Column(name = "res_observacao")
    private String observacao;

    public ResultadoSessao(Long id, Sessao sessao, Integer duracao, String observacao) {
        this.id = id;
        this.sessao = sessao;
        this.duracao = duracao;
        this.observacao = observacao;
    }

    public ResultadoSessao()
    {
        this(0L, null, 0, "");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
