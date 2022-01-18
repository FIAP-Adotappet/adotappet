package br.com.adotappet.adotappetapi.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NovoPedidoDTO {
    @NotBlank
    private final Long petId;
    @NotBlank
    private final Long usuarioId;
}
