package br.com.adotappet.adotappetapi.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    @Column(unique = true)
    private String email;

    private String senha;

    private LocalDate dataNascimento;

    private String cpf;

    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
}
