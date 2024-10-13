package com.example.simplesDental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO (Data Transfer Object) para representar os dados de um contato.
 * Onformações necessárias para criar ou atualizar um contato.
 */
@Data
public class ContatoDTO {

    /**
     * Nome do contato.
     * Este campo é obrigatório e deve ter no máximo 255 caracteres, valor default do BD.
     */
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres")
    private String nome;

    /**
     * Informação de contato
     * Este campo é obrigatório e deve ter no máximo 255 caracteres.
     */
    @NotBlank(message = "Contato é obrigatório")
    @Size(max = 255, message = "O contato deve ter no máximo 255 caracteres")
    private String contato;

    /**
     * ID do profissional associado a este contato.
     * Este campo é obrigatório.
     */
    @NotNull(message = "Profissional é obrigatório")
    private Long profissionalId;
}
