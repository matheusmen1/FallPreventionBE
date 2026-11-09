package com.unoeste.fallpreventionbe.entities;

public class Pacote
{
    private String acao;
    private String pacote_unity;
    private int repeticao;
    private float tempo;
    private boolean is_repeticao;
    private String sexo;
    private String mensagem;
    private Long sessao_id;
    private String data_hora_inicio;
    private String data_hora_fim;

    public Pacote(String acao, String pacote_unity, int repeticao, float tempo, boolean is_repeticao, String sexo, String mensagem, Long sessao_id, String data_hora_inicio, String data_hora_fim) {
        this.acao = acao;
        this.pacote_unity = pacote_unity;
        this.repeticao = repeticao;
        this.tempo = tempo;
        this.is_repeticao = is_repeticao;
        this.sexo = sexo;
        this.mensagem = mensagem;
        this.sessao_id = sessao_id;
        this.data_hora_inicio = data_hora_inicio;
        this.data_hora_fim = data_hora_fim;
    }

    public Pacote() {
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getPacote_unity() {
        return pacote_unity;
    }

    public void setPacote_unity(String pacote_unity) {
        this.pacote_unity = pacote_unity;
    }

    public int getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(int repeticao) {
        this.repeticao = repeticao;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }

    public boolean isIs_repeticao() {
        return is_repeticao;
    }

    public void setIs_repeticao(boolean is_repeticao) {
        this.is_repeticao = is_repeticao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getSessao_id() {
        return sessao_id;
    }

    public void setSessao_id(Long sessao_id) {
        this.sessao_id = sessao_id;
    }

    public String getData_hora_inicio() {
        return data_hora_inicio;
    }

    public void setData_hora_inicio(String data_hora_inicio) {
        this.data_hora_inicio = data_hora_inicio;
    }

    public String getData_hora_fim() {
        return data_hora_fim;
    }

    public void setData_hora_fim(String data_hora_fim) {
        this.data_hora_fim = data_hora_fim;
    }
}
