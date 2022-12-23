package br.com.miniautorizador.cartao;

import br.com.miniautorizador.cartao.controller.CartaoController;
import br.com.miniautorizador.cartao.dto.CartaoDTO;
import br.com.miniautorizador.cartao.service.CartaoService;
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
}
