package com.example.simplesDental.repositories;

import com.example.simplesDental.entities.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

    @Query("SELECT p FROM Profissional p WHERE " +
            "(LOWER(p.nome) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(p.cargo) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR TO_CHAR(p.nascimento, 'YYYY-MM-DD') LIKE CONCAT('%', :q, '%') " +
            "OR TO_CHAR(p.createdDate, 'YYYY-MM-DD') LIKE CONCAT('%', :q, '%'))")
    List<Profissional> findByQuery(@Param("q") String q);

}
