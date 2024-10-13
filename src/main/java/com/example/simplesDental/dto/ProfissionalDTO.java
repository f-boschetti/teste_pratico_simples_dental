package com.example.simplesDental.dto;

import com.example.simplesDental.anotations.ValidCargo;
import com.example.simplesDental.enums.Cargo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProfissionalDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres")
    private String nome;

    @ValidCargo(message = "Cargo é obrigatório e deve ser um valor válido")
    private Cargo cargo;

    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate nascimento;

    private List<ContatoDTO> contatos;
}
