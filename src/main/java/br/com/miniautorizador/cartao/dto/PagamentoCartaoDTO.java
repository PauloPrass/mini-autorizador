package br.com.miniautorizador.cartao.dto;

public class PagamentoCartaoDTO {
    private String numeroCartao;
    private String senha;
    private Double valor;

    public PagamentoCartaoDTO() {};

    public PagamentoCartaoDTO(String numeroCartao, String senha, Double valor) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.valor = valor;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}

