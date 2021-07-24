package br.com.adotappet.adotappetapi.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Pet {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private String raca;

    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Porte porte;

    private Sexo sexo;

    private Boolean disponivel;

    private String imagem;

    private String externalId;

    public enum Porte {
        GRANDE,
        MEDIO,
        PEQUENO
    }

    public enum Tipo {
        GATO,
        CACHORRO
    }

    public enum Sexo {
        MASCULINO,
        FEMININO
    }
}
