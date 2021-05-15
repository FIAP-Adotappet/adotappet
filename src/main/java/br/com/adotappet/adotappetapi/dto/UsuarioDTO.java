package br.com.adotappet.adotappetapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDTO {


    private static final long serialVersionUID = 1L;

    private Long id;

    private String nomeCompleto;

    private String email;

    private String senha;

    private LocalDate dataNascimento;

    private String cpf;

    private String telefone;

    private EnderecoDTO endereco;
}
