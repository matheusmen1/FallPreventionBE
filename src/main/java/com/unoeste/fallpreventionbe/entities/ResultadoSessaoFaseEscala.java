package com.unoeste.fallpreventionbe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "resultado_sessao_fase_escala")
public class ResultadoSessaoFaseEscala
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rse_id")
    private Long id;

    @Column(name = "rse_nome_escala", length = 100, nullable = false)
    private String nomeEscala;

    @Column(name = "rse_item_avaliado", length = 100)
    private String itemAvaliado;

    @Column(name = "rse_pontuacao", nullable = false)
    private Integer pontuacao;

    @Column(name = "rse_justificativa", columnDefinition = "TEXT")
    private String justificativa;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "resf_id", nullable = false, unique = true)
    private ResultadoSessaoFase resultadoSessaoFase;

    public ResultadoSessaoFaseEscala(
            Long id,
            String nomeEscala,
            String itemAvaliado,
            Integer pontuacao,
            String justificativa,
            ResultadoSessaoFase resultadoSessaoFase
    ) {
        this.id = id;
        this.nomeEscala = nomeEscala;
        this.itemAvaliado = itemAvaliado;
        this.pontuacao = pontuacao;
        this.justificativa = justificativa;
        this.resultadoSessaoFase = resultadoSessaoFase;
    }

    public ResultadoSessaoFaseEscala() {
        this(null, "Escala de Berg", "", 0, "", null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEscala() {
        return nomeEscala;
    }

    public void setNomeEscala(String nomeEscala) {
        this.nomeEscala = nomeEscala;
    }

    public String getItemAvaliado() {
        return itemAvaliado;
    }

    public void setItemAvaliado(String itemAvaliado) {
        this.itemAvaliado = itemAvaliado;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public ResultadoSessaoFase getResultadoSessaoFase() {
        return resultadoSessaoFase;
    }

    public void setResultadoSessaoFase(ResultadoSessaoFase resultadoSessaoFase) {
        this.resultadoSessaoFase = resultadoSessaoFase;
    }
}
