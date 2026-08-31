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
    @Column(name = "doe_descricao")
    private String descricao;

    public Doenca(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
    public Doenca()
    {
        this(0L, "", "");
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
