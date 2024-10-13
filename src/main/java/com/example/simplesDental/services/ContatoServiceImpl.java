package com.example.simplesDental.services;

import com.example.simplesDental.dto.ContatoDTO;
import com.example.simplesDental.entities.Contato;
import com.example.simplesDental.exceptions.ContatoNotFoundException;
import com.example.simplesDental.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Override
    public Contato save(ContatoDTO contatoDTO) {
        Contato contato = new Contato();
        contato.setNome(contatoDTO.getNome());
        contato.setContato(contatoDTO.getContato());

        return contatoRepository.save(contato);
    }

    @Override
    public List<Contato> findAll(String q, List<String> fields) {
        List<Contato> contatos = contatoRepository.findByQuery(q);

        if (fields != null && !fields.isEmpty()) {
            return contatos.stream()
                    .map(contato -> filterContatoByFields(contato, fields))
                    .collect(Collectors.toList());
        }
        return contatos;
    }

    @Override
    public Contato findById(Long id) {
        return contatoRepository.findById(id).orElse(null);
    }

    @Override
    public Contato update(Long id, ContatoDTO contatoDTO) {
        Contato contatoExistente = contatoRepository.findById(id).orElseThrow(() -> new ContatoNotFoundException(id));

        contatoExistente.setNome(contatoDTO.getNome());
        contatoExistente.setContato(contatoDTO.getContato());

        return contatoRepository.save(contatoExistente);
    }

    //Deveria ser uma exclusão logica
    @Override
    public void delete(Long id) {
        contatoRepository.deleteById(id);
    }

    private Contato filterContatoByFields(Contato contato, List<String> fields) {
        Contato filteredContato = new Contato();

        if (fields.contains("id")) {
            filteredContato.setId(contato.getId());
        }
        if (fields.contains("nome")) {
            filteredContato.setNome(contato.getNome());
        }
        if (fields.contains("contato")) {
            filteredContato.setContato(contato.getContato());
        }
        if (fields.contains("createdDate")) {
            filteredContato.setCreatedDate(contato.getCreatedDate());
        }

        return filteredContato;
    }
}
