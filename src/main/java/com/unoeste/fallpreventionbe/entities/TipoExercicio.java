package com.unoeste.fallpreventionbe.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_exercicio")
public class TipoExercicio
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tpe_id")
    private Long id;
    @Column(name = "tpe_nome")
    private String nome;

    public TipoExercicio(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TipoExercicio() {
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
