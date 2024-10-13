package com.example.simplesDental.services;

import com.example.simplesDental.dto.ProfissionalDTO;
import com.example.simplesDental.entities.Contato;
import com.example.simplesDental.entities.Profissional;
import com.example.simplesDental.exceptions.ResourceNotFoundException;
import com.example.simplesDental.repositories.ContatoRepository;
import com.example.simplesDental.repositories.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfissionalServiceImpl implements ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ContatoService contatoService;

    @Override
    public Profissional save(ProfissionalDTO profissionalDTO) {
        Profissional profissional = new Profissional();
        profissional.setNome(profissionalDTO.getNome());
        profissional.setCargo(profissionalDTO.getCargo());
        profissional.setNascimento(profissionalDTO.getNascimento());

        if (profissionalDTO.getContatos() != null) {
            List<Contato> contatos = profissionalDTO.getContatos().stream().map(contatoDTO -> {
                Contato contato = new Contato();
                contato.setNome(contatoDTO.getNome());
                contato.setContato(contatoDTO.getContato());
                contato.setProfissional(profissional);
                return contato;
            }).collect(Collectors.toList());

            profissional.setContatos(contatos);
        }

        return profissionalRepository.save(profissional);
    }

    @Override
    public Profissional update(Long id, ProfissionalDTO profissionalDTO) {
        Profissional profissionalExistente = profissionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional com ID " + id + " não encontrado"));

        if (profissionalDTO.getNome() != null && !profissionalDTO.getNome().isEmpty()) {
            profissionalExistente.setNome(profissionalDTO.getNome());
        }
        if (profissionalDTO.getCargo() != null) {
            profissionalExistente.setCargo(profissionalDTO.getCargo());
        }
        if (profissionalDTO.getNascimento() != null) {
            profissionalExistente.setNascimento(profissionalDTO.getNascimento());
        }
        return profissionalRepository.save(profissionalExistente);
    }

    @Override
    public List<Profissional> findAll(String q, List<String> fields) {
        List<Profissional> prof = profissionalRepository.findByQuery(q);

        if (fields != null && !fields.isEmpty()) {
            return prof.stream()
                    .map(contato -> filterContatoByFields(contato, fields))
                    .collect(Collectors.toList());
        }
        return prof;
    }

    @Override
    public Profissional findById(Long id) {
        return profissionalRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Profissional profissionalExistente = profissionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional com ID " + id + " não encontrado"));

        profissionalExistente.setAtivo(false);
        List<Contato> contatos = profissionalExistente.getContatos();
        // Aqui também deveria ser uma exclusão lógica
        contatoService.deletarContatos(contatos);
        profissionalRepository.save(profissionalExistente);
    }

    private Profissional filterContatoByFields(Profissional professional, List<String> fields) {
        Profissional filteredProf = new Profissional();

        if (fields.contains("id")) {
            filteredProf.setId(professional.getId());
        }
        if (fields.contains("nome")) {
            filteredProf.setNome(professional.getNome());
        }
        if (fields.contains("cargo")) {
            filteredProf.setCargo(professional.getCargo());
        }
        if (fields.contains("createdDate")) {
            filteredProf.setCreatedDate(professional.getCreatedDate());
        }
        if(fields.contains("nascimento")) {
            filteredProf.setNascimento(professional.getNascimento());
        }
        if(fields.contains("contatos")) {
            filteredProf.setContatos(professional.getContatos());
        }

        return filteredProf;
    }
}
