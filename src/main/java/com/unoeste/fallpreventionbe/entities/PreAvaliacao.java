package com.unoeste.fallpreventionbe.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pre_avaliacao")
public class PreAvaliacao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pre_id")
    private Long id;
    @Column(name = "pre_data_avaliacao")
    private LocalDateTime data_avaliacao;
    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "pac_id")
    private Paciente paciente;

    public PreAvaliacao()
    {
        this(0L, null, null, null);
    }

    public PreAvaliacao(Long id, LocalDateTime data_avaliacao, Usuario usuario, Paciente paciente) {
        this.id = id;
        this.data_avaliacao = data_avaliacao;
        this.usuario = usuario;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData_avaliacao() {
        return data_avaliacao;
    }

    public void setData_avaliacao(LocalDateTime data_avaliacao) {
        this.data_avaliacao = data_avaliacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
