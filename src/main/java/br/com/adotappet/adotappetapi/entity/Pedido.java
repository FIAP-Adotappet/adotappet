package br.com.adotappet.adotappetapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pet pet;

    @ManyToOne
    private Usuario usuario;

    private Status status;

    private String motivo;

    private LocalDateTime dataSolicitacao;

    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        dataSolicitacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

    public enum Status {
        ABERTO,
        ACEITO,
        REJEITADO,
        FINALIZADO,
        CANCELADO
    }
}
