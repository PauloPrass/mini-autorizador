package br.com.miniautorizador.cartao;

import br.com.miniautorizador.cartao.controller.CartaoController;
import br.com.miniautorizador.cartao.dto.CartaoDTO;
import br.com.miniautorizador.cartao.dto.PagamentoCartaoDTO;
import br.com.miniautorizador.cartao.service.CartaoService;
import br.com.miniautorizador.exceptions.SaldoInsuficienteException;
import br.com.miniautorizador.exceptions.SenhaIncorretaException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.NoResultException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(CartaoController.class)
public class CartaoControllerTest {

    @MockBean
    private CartaoService cartaoService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveCriarCartao() throws Exception {
        when(cartaoService.salva(any(String.class))).thenReturn(CartaoUtil.criaCartaoDTO());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/cartoes/")
                .content(mapper.writeValueAsString(CartaoUtil.criaSenhaCartaoValida()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void deveDarErroAoCriarCartao() throws Exception {
        when(cartaoService.salva(any(String.class)));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/cartoes/")
                        .content(mapper.writeValueAsString(CartaoUtil.criaSenhaCartaoInvalida()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deveBuscarSaldoCartao() throws Exception {
        when(cartaoService.buscaSaldo(any(String.class))).thenReturn(CartaoUtil.buscaCartaoDTO());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/cartoes/2222444466668888"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveFazerTransacao() throws Exception {
        when(cartaoService.realizaPagamento(any(PagamentoCartaoDTO.class))).thenReturn(CartaoUtil.criaCartaoDTO());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/cartoes/transacoes")
                        .content(mapper.writeValueAsString(CartaoUtil.criaPagamentoDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void deveDarErroAoBuscarCartao() throws Exception {
        when(cartaoService.realizaPagamento(any(PagamentoCartaoDTO.class)))
                .thenThrow(new NoResultException());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/cartoes/transacoes")
                        .content(mapper.writeValueAsString(CartaoUtil.criaPagamentoCartaoInvalido()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deveDarErroSenhaInvalida() throws Exception {
        when(cartaoService.realizaPagamento(any(PagamentoCartaoDTO.class)))
                .thenThrow(new SenhaIncorretaException("Senha incorreta"));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/cartoes/transacoes")
                        .content(mapper.writeValueAsString(CartaoUtil.criaPagamentoSenhaInvalida()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    public void deveDarErroSaldoInsuficiente() throws Exception {
        when(cartaoService.realizaPagamento(any(PagamentoCartaoDTO.class)))
                .thenThrow(new SaldoInsuficienteException("Saldo insuficiente"));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/cartoes/transacoes")
                        .content(mapper.writeValueAsString(CartaoUtil.criaPagamentoSaldoInsuficiente()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }
}
