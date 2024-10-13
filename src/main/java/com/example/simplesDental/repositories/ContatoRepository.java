package com.example.simplesDental.repositories;

import com.example.simplesDental.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório para gerenciar as operações de persistência da entidade {@link Contato}.
 * Esta interface estende JpaRepository
 */
@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    /**
     * Busca contatos que correspondem ao critério
     * @param q A string de pesquisa usada para filtrar contatos pelo nome ou dado de contato.
     * @return Uma lista de contatos
     */
    @Query("SELECT c FROM Contato c WHERE " +
            " (LOWER(c.nome) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(c.contato) LIKE LOWER(CONCAT('%', :q, '%')))")
    List<Contato> findByQuery(@Param("q") String q);
}
