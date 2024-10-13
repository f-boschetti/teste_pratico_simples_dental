package com.example.simplesDental.entities;

import com.example.simplesDental.enums.Cargo;
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

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL)
    private List<Contato> contatos;
}
