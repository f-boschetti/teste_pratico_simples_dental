package com.example.simplesDental.services;

import com.example.simplesDental.dto.ProfissionalDTO;
import com.example.simplesDental.entities.Profissional;

import java.util.List;

public interface ProfissionalService {

    Profissional save(ProfissionalDTO profissionalDTO);

    List<Profissional> findAll(String q, List<String> fields);

    Profissional findById(Long id);

    Profissional update(Long id, ProfissionalDTO profissionalDTO);

    void delete(Long id);
}
