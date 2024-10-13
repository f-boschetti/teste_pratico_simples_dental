package com.example.simplesDental.entities;

import com.example.simplesDental.enums.Cargo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @Column(nullable = false)
    private LocalDate nascimento;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDate createdDate = LocalDate.now();

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Contato> contatos;

    @Column(nullable = false)
    private boolean ativo = true;

}
