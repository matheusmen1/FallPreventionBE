package com.unoeste.fallpreventionbe.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "metrica")
public class Metrica
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "met_id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pre_id")
    private PreAvaliacao pre_avaliacao;
    @Column(name = "met_nome_teste")
    private String nome_teste;
    @Column(name = "met_pontuacao")
    private double pontuacao;

    public Metrica()
    {
        this(0L, null, "", 0);
    }

    public Metrica(Long id, PreAvaliacao pre_avaliacao, String nome_teste, double pontuacao) {
        this.id = id;
        this.pre_avaliacao = pre_avaliacao;
        this.nome_teste = nome_teste;
        this.pontuacao = pontuacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @JsonIgnore
    public PreAvaliacao getPreAvaliacao() {
        return pre_avaliacao;
    }

    public void setPreAvaliacao(PreAvaliacao pre_avaliacao) {
        this.pre_avaliacao = pre_avaliacao;
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
