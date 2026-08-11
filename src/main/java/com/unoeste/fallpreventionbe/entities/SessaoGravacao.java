package com.unoeste.fallpreventionbe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessao_gravacao")
public class SessaoGravacao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sesg_id")
    private Long id;
    @Column(name = "sesg_caminho_arquivo")
    private String caminho_arquivo;
    @Column(name = "sesg_data_hora")
    private LocalDateTime data_hora;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ses_id")
    private Sessao sessao;

    public SessaoGravacao(Long id, String caminho_arquivo, LocalDateTime data_hora, Sessao sessao) {
        this.id = id;
        this.caminho_arquivo = caminho_arquivo;
        this.data_hora = data_hora;
        this.sessao = sessao;
    }

    public SessaoGravacao()
    {
        this(0L, "", null, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaminho_arquivo() {
        return caminho_arquivo;
    }

    public void setCaminho_arquivo(String caminho_arquivo) {
        this.caminho_arquivo = caminho_arquivo;
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
