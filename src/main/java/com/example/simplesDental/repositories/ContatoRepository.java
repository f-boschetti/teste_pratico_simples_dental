package com.example.simplesDental.repositories;

import com.example.simplesDental.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    @Query("SELECT c FROM Contato c WHERE (LOWER(c.nome) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(c.contato) LIKE LOWER(CONCAT('%', :q, '%')))")
    List<Contato> findByQuery(@Param("q") String q);
}
