package br.com.miniautorizador.cartao;

import br.com.miniautorizador.cartao.dto.CartaoDTO;
import br.com.miniautorizador.cartao.dto.PagamentoCartaoDTO;
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

    public static CartaoDTO buscaCartaoDTO() {
        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setNumeroCartao("2222444466668888");
        cartaoDTO.setSaldo(500.0);
        return cartaoDTO;
    }

    public static PagamentoCartaoDTO criaPagamentoDTO() {
        PagamentoCartaoDTO pagamentoCartaoDTO = new PagamentoCartaoDTO();
        pagamentoCartaoDTO.setNumeroCartao("2222444466668888");
        pagamentoCartaoDTO.setSenha("12345678");
        pagamentoCartaoDTO.setValor(100.0);
        return pagamentoCartaoDTO;
    }

    public static PagamentoCartaoDTO criaPagamentoSaldoInsuficiente() {
        PagamentoCartaoDTO pagamentoCartaoDTO = new PagamentoCartaoDTO();
        pagamentoCartaoDTO.setNumeroCartao("2222444466668888");
        pagamentoCartaoDTO.setSenha("12345678");
        pagamentoCartaoDTO.setValor(1000.0);
        return pagamentoCartaoDTO;
    }

    public static PagamentoCartaoDTO criaPagamentoCartaoInvalido() {
        PagamentoCartaoDTO pagamentoCartaoDTO = new PagamentoCartaoDTO();
        pagamentoCartaoDTO.setNumeroCartao("1111444466668888");
        pagamentoCartaoDTO.setSenha("12345678");
        pagamentoCartaoDTO.setValor(100.0);
        return pagamentoCartaoDTO;
    }

    public static PagamentoCartaoDTO criaPagamentoSenhaInvalida() {
        PagamentoCartaoDTO pagamentoCartaoDTO = new PagamentoCartaoDTO();
        pagamentoCartaoDTO.setNumeroCartao("2222444466668888");
        pagamentoCartaoDTO.setSenha("12345678910");
        pagamentoCartaoDTO.setValor(100.0);
        return pagamentoCartaoDTO;
    }
}
