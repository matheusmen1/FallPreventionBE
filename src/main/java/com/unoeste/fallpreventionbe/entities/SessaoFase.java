package com.unoeste.fallpreventionbe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sessao_fase")
public class SessaoFase
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sesf_id")
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ses_id")
    private Sessao sessao;
    @ManyToOne
    @JoinColumn(name = "exe_id")
    private Exercicio exercicio;
    @Column(name = "sesf_ordem")
    private Integer ordem;
    @Column(name = "sesf_repeticao")
    private Integer repeticao;

    public SessaoFase(Long id, Sessao sessao, Exercicio exercicio, Integer ordem, Integer repeticao) {
        this.id = id;
        this.sessao = sessao;
        this.exercicio = exercicio;
        this.ordem = ordem;
        this.repeticao = repeticao;
    }

    public SessaoFase()
    {
        this(0L, null, null, 0, 0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Integer getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(Integer repeticao) {
        this.repeticao = repeticao;
    }
}
