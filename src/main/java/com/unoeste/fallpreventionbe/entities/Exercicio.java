package com.unoeste.fallpreventionbe.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "exercicio")
public class Exercicio
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exe_id")
    private Long id;
    @Column(name = "exe_nome")
    private String nome;
    @ManyToOne
    @JoinColumn(name = "tpe_id")
    private TipoExercicio tipoExercicio;
    @Column(name = "exe_codigo_nome") // referencia dos códigos da unity
    private String codigo_nome;


    public Exercicio(Long id, String nome, TipoExercicio tipoExercicio, String codigo_nome) {
        this.id = id;
        this.nome = nome;
        this.tipoExercicio = tipoExercicio;
        this.codigo_nome = codigo_nome;
    }

    public Exercicio() {
        this(0L, "", null, "");
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

    public TipoExercicio getTipoExercicio() {
        return tipoExercicio;
    }

    public void setTipoExercicio(TipoExercicio tipoExercicio) {
        this.tipoExercicio = tipoExercicio;
    }

    public String getCodigo_nome() {
        return codigo_nome;
    }

    public void setCodigo_nome(String codigo_nome) {
        this.codigo_nome = codigo_nome;
    }
}
