package com.unoeste.fallpreventionbe.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "configuracao")
public class Configuracao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cfg_id")
    private Long id;
    @Column(name = "cfg_ip")
    private String ip;
    @Column(name = "cfg_flag")
    private Boolean flag;

    public Configuracao(String ip, Boolean flag) {
        this.ip = ip;
        this.flag = flag;
    }

    public Configuracao() {
        this("", false);
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
