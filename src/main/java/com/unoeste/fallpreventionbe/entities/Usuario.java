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
    @Column(name = "usr_login")
    private String login;
    @Column(name = "usr_nome")
    private String nome;
    @Column(name = "usr_senha")
    private String senha;
    @Column(name = "usr_email")
    private String email;
    @Column(name = "usr_telefone")
    private String telefone;
    @Column(name = "usr_ra")
    private int ra;
    @Column(name = "usr_nivel")
    private int nivel; // 1 - fisio 0 - monitor
    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usuario responsavel;
    public Usuario()
    {
        this(0L, "", "", "", "", "", 0 , 0, null);
    }

    public Usuario(Long id, String login, String nome, String senha, String email, String telefone, int ra, int nivel, Usuario responsavel) {
        this.id = id;
        this.login = login;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
