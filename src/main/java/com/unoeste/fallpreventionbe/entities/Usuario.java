package com.unoeste.fallpreventionbe.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;
    @Column(name = "usr_cpf")
    private String cpf;
    @Column(name = "usr_nome")
    private String nome;
    @Column(name = "usr_senha")
    private String senha;
    @Column(name = "usr_email")
    private String email;
    @Column(name = "usr_telefone")
    private String telefone;
    @Column(name = "usr_ra")
    private String ra;
    @Column(name = "usr_nivel")
    private Integer nivel; // 1 - fisio 0 - monitor
    @ManyToOne
    @JoinColumn(name = "usr_responsavel_id")
    private Usuario responsavel;
    public Usuario()
    {
        this(0L, "", "", "", "", "", "" , 0, null);
    }

    public Usuario(Long id, String cpf, String nome, String senha, String email, String telefone, String ra, Integer nivel, Usuario responsavel) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.ra = ra;
        this.nivel = nivel;
        this.responsavel = responsavel;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
}
