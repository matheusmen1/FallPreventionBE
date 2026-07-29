package com.unoeste.fallpreventionbe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "aprovacao_sessao")
public class AprovacaoSessao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apr_id")
    private Long id;
    @Column(name = "apr_data_hora")
    private LocalDateTime data_hora;
    @Column(name = "apr_motivo")
    private String motivo; // justificar motivo de recusamento
    @Column(name = "apr_status")
    private String status; // aprovada, recusada

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "ses_id")
    private Sessao sessao;

    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usuario fisioterapeuta;

    public AprovacaoSessao(Long id, LocalDateTime data_hora, String motivo, String status, Sessao sessao, Usuario fisioterapeuta) {
        this.id = id;
        this.data_hora = data_hora;
        this.motivo = motivo;
        this.status = status;
        this.sessao = sessao;
        this.fisioterapeuta = fisioterapeuta;
    }

    public AprovacaoSessao()
    {
        this(0L, null, "", "", null, null);
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String observacao) {
        this.motivo = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Usuario getFisioterapeuta() {
        return fisioterapeuta;
    }

    public void setFisioterapeuta(Usuario fisioterapeuta) {
        this.fisioterapeuta = fisioterapeuta;
    }
}
