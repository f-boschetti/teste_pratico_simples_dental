package com.example.simplesDental.services;

import com.example.simplesDental.dto.ContatoDTO;
import com.example.simplesDental.entities.Contato;

import java.util.List;

public interface ContatoService {

    Contato save(ContatoDTO contato);

    List<Contato> findAll(String q, List<String> fields);

    Contato findById(Long id);

    Contato update(Long id, ContatoDTO contato);

    void delete(Long id);
}
