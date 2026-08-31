package com.unoeste.fallpreventionbe.entities;

public class Pacote
{
    private String acao;
    private String pacote_unity;
    private int repeticao;
    private float tempo;
    private boolean is_repeticao;
    private String mensagem;
    private Long sessao_id;

    public Pacote(String acao, String pacote_unity, int repeticao, float tempo, boolean is_repeticao, String mensagem, Long sessao_id) {
        this.acao = acao;
        this.pacote_unity = pacote_unity;
        this.repeticao = repeticao;
        this.tempo = tempo;
        this.is_repeticao = is_repeticao;
        this.mensagem = mensagem;
        this.sessao_id = sessao_id;
    }

    public Pacote()
    {
        this("", "", 0, 0f, false, "",  0L);
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
}
