package com.unoeste.fallpreventionbe.dto;

public class RelatorioEscalaDTO
{
    private Long id;
    private String nomeEscala;
    private String itemAvaliado;
    private Integer pontuacao;
    private String justificativa;

    public RelatorioEscalaDTO() {
    }

    public RelatorioEscalaDTO(Long id, String nomeEscala, String itemAvaliado, Integer pontuacao, String justificativa) {
        this.id = id;
        this.nomeEscala = nomeEscala;
        this.itemAvaliado = itemAvaliado;
        this.pontuacao = pontuacao;
        this.justificativa = justificativa;
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
}
