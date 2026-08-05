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
    @Column(name = "exe_descricao")
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "tpe_id")
    private TipoExercicio tipo_exercicio;
    @Column(name = "exe_codigo_nome") // referencia dos códigos da unity
    private String codigo_nome;


    public Exercicio(Long id, String nome, String descricao, TipoExercicio tipo_exercicio, String codigo_nome) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo_exercicio = tipo_exercicio;
        this.codigo_nome = codigo_nome;
    }

    public Exercicio() {
        this(0L, "", "",null, "");
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

    public TipoExercicio getTipo_exercicio() {
        return tipo_exercicio;
    }

    public void setTipo_exercicio(TipoExercicio tipo_exercicio) {
        this.tipo_exercicio = tipo_exercicio;
    }

    public String getCodigo_nome() {
        return codigo_nome;
    }

    public void setCodigo_nome(String codigo_nome) {
        this.codigo_nome = codigo_nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
