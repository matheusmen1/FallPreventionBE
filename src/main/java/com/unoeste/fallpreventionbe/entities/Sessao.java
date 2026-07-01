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
    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usuario responsavel;
    @ManyToOne
    @JoinColumn(name = "pac_id")
    private Paciente paciente;
    @Column(name = "ses_status")
    private String status; // concluida, pendente, cancelada
    @OneToMany (mappedBy = "sessao")
    private List<AprovacaoSessao> aprovacaoSessaos;

    public Sessao(Long id, LocalDateTime data_hora, Usuario responsavel, Paciente paciente, String status) {
        this.id = id;
        this.data_hora = data_hora;
        this.responsavel = responsavel;
        this.paciente = paciente;
        this.status = status;
    }

    public Sessao()
    {
        this(0L, null, null, null, "");
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

    public List<AprovacaoSessao> getAprovacaoSessaos() {
        return aprovacaoSessaos;
    }

    public void setAprovacaoSessaos(List<AprovacaoSessao> aprovacaoSessaos) {
        this.aprovacaoSessaos = aprovacaoSessaos;
    }
}
