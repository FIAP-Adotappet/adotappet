package br.com.adotappet.adotappetapi.dto;

import lombok.Data;

@Data
public class EnderecoDTO {

    private Long id;

    private String logradouro;

    private Integer numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private TipoResidencia tipoResidencia;

    public enum TipoResidencia {
        CASA_SEM_QUINTAL,
        CASA_COM_QUINTAL,
        APTO,
        CHACARA
    }
}
