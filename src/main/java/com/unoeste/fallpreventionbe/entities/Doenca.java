package com.unoeste.fallpreventionbe.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "doenca")
public class Doenca
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doe_id")
    private Long id;
    @Column(name = "doe_nome")
    private String nome;

    public Doenca(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public Doenca()
    {
        this(0L, "");
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
