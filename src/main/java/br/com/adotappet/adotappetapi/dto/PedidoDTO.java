package br.com.adotappet.adotappetapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PedidoDTO {

    private Long id;

    private PetDTO pet;

    private UsuarioDTO usuario;

    private Status status;

    private String motivo;

    private LocalDateTime dataSolicitacao;

    private LocalDateTime dataAtualizacao;

    public enum Status {
        ABERTO,
        ACEITO,
        REJEITADO,
        FINALIZADO,
        CANCELADO
    }
}
