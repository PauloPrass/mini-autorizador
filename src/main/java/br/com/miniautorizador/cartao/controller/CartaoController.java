package br.com.miniautorizador.cartao.controller;

import br.com.miniautorizador.cartao.dto.CartaoDTO;
import br.com.miniautorizador.cartao.dto.SenhaCartaoDTO;
import br.com.miniautorizador.cartao.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping("/")
    public ResponseEntity<?> criaCartao(@RequestBody SenhaCartaoDTO senhaCartaoDTO) {
        try {
            CartaoDTO cartaoDTO = cartaoService.salva(senhaCartaoDTO.getSenha());
            return ResponseEntity.status(HttpStatus.CREATED).body(cartaoDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Informe uma senha com pelo menos 6 dígitos");
        }
    }
}
