package com.example.simplesDental.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Classe que representa um contato associado a um profissional.
 */
@Entity
@Data
public class Contato {

    /**
     * Identificador único do contato.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do contato.
     * Este campo é obrigatório.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * Informação de contato.
     * Este campo é obrigatório.
     */
    @Column(nullable = false)
    private String contato;

    /**
     * Data de criação do contato.
     * Este campo é definido automaticamente para a data atual no momento da criação.
     */
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDate createdDate = LocalDate.now();


    /**
     * Profissional associado a este contato.
     * Este campo é obrigatório e estabelece uma relação com a entidade {@link Profissional}.
     */
    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    @JsonBackReference
    private Profissional profissional;

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", contato='" + contato + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
