package br.com.miniautorizador.cartao.dto;

import br.com.miniautorizador.cartao.model.Cartao;

public class CartaoDTO {

    private String numeroCartao;
    private Double saldo;

    public static CartaoDTO converter(Cartao cartao) {
        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.numeroCartao = cartao.getNumeroCartao();
        cartaoDTO.saldo = cartao.getSaldo();
        return cartaoDTO;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }
}
