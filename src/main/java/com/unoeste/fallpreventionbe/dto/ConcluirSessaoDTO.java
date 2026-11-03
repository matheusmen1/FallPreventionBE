package com.unoeste.fallpreventionbe.dto;

public class ConcluirSessaoDTO
{
    private String observacao;

    public ConcluirSessaoDTO() {
    }

    public ConcluirSessaoDTO(String observacao) {
        this.observacao = observacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
