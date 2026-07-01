package com.unoeste.fallpreventionbe.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "avatar_paciente")
public class AvatarPaciente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ava_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "pac_id")
    private Paciente paciente;
    @Column(name = "ava_url")
    private String url;

    public AvatarPaciente(Long id, Paciente paciente, String url) {
        this.id = id;
        this.paciente = paciente;
        this.url = url;
    }

    public AvatarPaciente()
    {
        this(0L, null, "");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
