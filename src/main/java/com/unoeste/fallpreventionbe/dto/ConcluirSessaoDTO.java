package com.unoeste.fallpreventionbe.dto;

public class ConcluirSessaoDTO
{
    private String observacao;
    private Long usuarioId;

    public ConcluirSessaoDTO() {
    }

    public ConcluirSessaoDTO(String observacao, Long usuarioId) {
        this.observacao = observacao;
        this.usuarioId = usuarioId;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
