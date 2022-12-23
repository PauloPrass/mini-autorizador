package br.com.miniautorizador.cartao;

import br.com.miniautorizador.cartao.dto.CartaoDTO;
import br.com.miniautorizador.cartao.dto.SenhaCartaoDTO;

public class CartaoUtil {

    public static CartaoDTO criaCartaoDTO() {
        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setNumeroCartao("2222444466668888");
        cartaoDTO.setSaldo(500.0);
        return cartaoDTO;
    }

    public static SenhaCartaoDTO criaSenhaCartaoValida() {
        SenhaCartaoDTO senhaCartaoDTO = new SenhaCartaoDTO();
        senhaCartaoDTO.setSenha("12345678");
        return senhaCartaoDTO;
    }

    public static SenhaCartaoDTO criaSenhaCartaoInvalida() {
        SenhaCartaoDTO senhaCartaoDTO = new SenhaCartaoDTO();
        senhaCartaoDTO.setSenha(null);
        return senhaCartaoDTO;
    }
}
