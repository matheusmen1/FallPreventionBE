package com.unoeste.fallpreventionbe.entities;

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
    private FichaAvaliacao ficha_avaliacao;
    @Column(name = "met_nome_teste")
    private String nome_teste;
    @Column(name = "met_pontuacao")
    private double pontuacao;

    public Metrica()
    {
        this(0L, null, "", 0);
    }

    public Metrica(Long id, FichaAvaliacao ficha_avaliacao, String nome_teste, double pontuacao) {
        this.id = id;
        this.ficha_avaliacao = ficha_avaliacao;
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
    public FichaAvaliacao getFicha_avaliacao() {
        return ficha_avaliacao;
    }

    public void setFicha_avaliacao(FichaAvaliacao ficha_avaliacao) {
        this.ficha_avaliacao = ficha_avaliacao;
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
