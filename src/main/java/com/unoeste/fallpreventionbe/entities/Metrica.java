package com.unoeste.fallpreventionbe.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "metrica")
public class Metrica
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "met_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "pre_id")
    private PreAvaliacao preAvaliacao;
    @Column(name = "met_nome_teste")
    private String nome_teste;
    @Column(name = "met_pontuacao")
    private double pontuacao;

    public Metrica()
    {
        this(0L, null, "", 0);
    }

    public Metrica(Long id, PreAvaliacao preAvaliacao, String nome_teste, double pontuacao) {
        this.id = id;
        this.preAvaliacao = preAvaliacao;
        this.nome_teste = nome_teste;
        this.pontuacao = pontuacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PreAvaliacao getPreAvaliacao() {
        return preAvaliacao;
    }

    public void setPreAvaliacao(PreAvaliacao preAvaliacao) {
        this.preAvaliacao = preAvaliacao;
    }

    public String getNome_teste() {
        return nome_teste;
    }

    public void setNome_teste(String nome_teste) {
        this.nome_teste = nome_teste;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }
}
