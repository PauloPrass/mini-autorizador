package br.com.miniautorizador.cartao.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class CartaoCustomRepository {

    private final EntityManager em;

    public CartaoCustomRepository(EntityManager em) {
        this.em = em;
    }

    public String buscaProximoNumeroCartao() {
        return Optional.ofNullable(em.createQuery(
                                "select max(c.numeroCartao) from Cartao c", String.class)
                        .getSingleResult())
                .map(ultimoCodigo -> String.format("%016d", (Long.parseLong(ultimoCodigo) + 1)))
                .orElse("0000000000000001");
    }
}