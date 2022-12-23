package br.com.miniautorizador.cartao.service;

import br.com.miniautorizador.cartao.dto.CartaoDTO;
import br.com.miniautorizador.cartao.model.Cartao;
import br.com.miniautorizador.cartao.repository.CartaoCustomRepository;
import br.com.miniautorizador.cartao.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
