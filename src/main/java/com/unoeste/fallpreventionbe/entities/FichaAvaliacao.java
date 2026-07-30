package com.unoeste.fallpreventionbe.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ficha_avaliacao")
public class FichaAvaliacao
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

    @OneToMany(mappedBy = "ficha_avaliacao")
    private List<Metrica> metricas;

    public FichaAvaliacao()
    {
        this(0L, null, null, null);
    }

    public FichaAvaliacao(Long id, LocalDateTime data_avaliacao, Usuario usuario, Paciente paciente) {
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

    public List<Metrica> getMetricas() {
        return metricas;
    }

    public void setMetricas(List<Metrica> metricas) {
        this.metricas = metricas;
    }
}
