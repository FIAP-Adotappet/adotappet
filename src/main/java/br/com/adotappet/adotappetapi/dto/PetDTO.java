package br.com.adotappet.adotappetapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PetDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private Tipo tipo;

    private String raca;

    private LocalDate dataNascimento;

    private Porte porte;

    private Sexo sexo;


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
