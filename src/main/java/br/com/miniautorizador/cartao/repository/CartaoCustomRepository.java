package br.com.miniautorizador.cartao.repository;

import br.com.miniautorizador.cartao.model.Cartao;
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

    public Cartao buscaCartaoPeloNumero(String numeroCartao) {

        String query = "select C from Cartao as C ";
        String condicao = "where";

        if(numeroCartao != null) {
            query += condicao + " C.numeroCartao = :numeroCartao";
        }


        var q = em.createQuery(query, Cartao.class);

        if(numeroCartao != null) {
            q.setParameter("numeroCartao", numeroCartao);
        }

        return q.getSingleResult();
    }
}