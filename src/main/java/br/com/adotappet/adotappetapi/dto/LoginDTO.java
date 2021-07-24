package br.com.adotappet.adotappetapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {
    @NotBlank
    private final String email;
    @NotBlank
    private final String senha;
}
