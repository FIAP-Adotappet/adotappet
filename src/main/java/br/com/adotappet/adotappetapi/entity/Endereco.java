package br.com.adotappet.adotappetapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Endereco {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;

    private Integer numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    @Enumerated(EnumType.STRING)
    private TipoResidencia tipoResidencia;

    public enum TipoResidencia {
        CASA_SEM_QUINTAL,
        CASA_COM_QUINTAL,
        APTO,
        CHACARA
    }
}
