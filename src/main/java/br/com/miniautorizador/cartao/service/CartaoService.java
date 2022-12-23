package br.com.miniautorizador.cartao.service;

import br.com.miniautorizador.cartao.dto.CartaoDTO;
import br.com.miniautorizador.cartao.dto.PagamentoCartaoDTO;
import br.com.miniautorizador.cartao.model.Cartao;
import br.com.miniautorizador.cartao.repository.CartaoCustomRepository;
import br.com.miniautorizador.cartao.repository.CartaoRepository;
import br.com.miniautorizador.exceptions.SaldoInsuficienteException;
import br.com.miniautorizador.exceptions.SenhaIncorretaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoCustomRepository cartaoCustomRepository;

    public CartaoDTO salva(String senha) throws RuntimeException {
        if(senha.length() < 6) throw new RuntimeException("Senha deve possuir mais de 6 dígitos");
        Cartao cartao = new Cartao();
        cartao.setSenha(senha);
        cartao.setNumeroCartao(cartaoCustomRepository.buscaProximoNumeroCartao());
        cartao.setSaldo(500.00);
        cartaoRepository.save(cartao);
        return new CartaoDTO().converter(cartao);
    }

    public CartaoDTO buscaSaldo(String numeroCartao) {
        Cartao cartao = cartaoCustomRepository.buscaCartaoPeloNumero(numeroCartao);
        return new CartaoDTO().converter(cartao);
    }

    public synchronized CartaoDTO realizaPagamento(PagamentoCartaoDTO verificaCartaoDTO) {
        Cartao cartao = cartaoCustomRepository.buscaCartaoPeloNumero(verificaCartaoDTO.getNumeroCartao());
        Double valor = new BigDecimal(verificaCartaoDTO.getValor())
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
        if(!cartao.getSenha().equals(verificaCartaoDTO.getSenha()))
            throw new SenhaIncorretaException("Senha incorreta");
        if(cartao.getSaldo() - valor < 0.0)
            throw new SaldoInsuficienteException("Saldo insuficiente");
        cartao.setSaldo(cartao.getSaldo() - valor);
        cartaoRepository.save(cartao);
        return new CartaoDTO().converter(cartao);
    }
}
