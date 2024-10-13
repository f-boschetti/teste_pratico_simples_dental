package com.example.simplesDental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContatoDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres")
    private String nome;

    @NotBlank(message = "Contato é obrigatório")
    @Size(max = 255, message = "O contato deve ter no máximo 255 caracteres")
    private String contato;

    @NotNull(message = "Profissional é obrigatório")
    private Long profissionalId;
}
