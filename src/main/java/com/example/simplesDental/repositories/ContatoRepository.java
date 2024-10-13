package com.example.simplesDental.repositories;

import com.example.simplesDental.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
