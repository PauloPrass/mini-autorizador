package br.com.miniautorizador.cartao.dto;

public class SenhaCartaoDTO {

    private String senha;

    public SenhaCartaoDTO() {};

    public SenhaCartaoDTO(String senha) {
        this.senha = senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }
}
