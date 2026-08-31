package com.unoeste.fallpreventionbe.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "paciente")
public class Paciente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pac_id")
    private Long id;
    @Column(name = "pac_nome")
    private String nome;
    @Column(name = "pac_email")
    private String email;
    @Column(name = "pac_data_nascimento")
    private LocalDate data_nascimento;
    @Column(name = "pac_telefone")
    private String telefone;
    @Column(name = "pac_cpf")
    private String cpf;
    @Column(name = "pac_tipo_pessoa")
    private String tipo_pessoa;
    @Column(name = "pac_observacao")
    private String observacao;
    @ManyToOne
    @JoinColumn(name = "doe_id")
    private Doenca doenca;

    public Paciente(Long id, String nome, String email, LocalDate data_nascimento, String telefone, String cpf, String tipo_pessoa, Doenca doenca, String observacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
        this.cpf = cpf;
        this.tipo_pessoa = tipo_pessoa;
        this.doenca = doenca;
        this.observacao = observacao;
    }
    public Paciente()
    {
        this(0L, "", "", null, "", "", "", null, "");
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTipo_pessoa() {
        return tipo_pessoa;
    }

    public void setTipo_pessoa(String tipo_pessoa) {
        this.tipo_pessoa = tipo_pessoa;
    }

    public Doenca getDoenca() {
        return doenca;
    }

    public void setDoenca(Doenca doenca) {
        this.doenca = doenca;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
