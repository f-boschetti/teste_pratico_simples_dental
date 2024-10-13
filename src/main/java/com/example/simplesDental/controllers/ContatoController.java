package com.example.simplesDental.controllers;

import com.example.simplesDental.dto.ContatoDTO;
import com.example.simplesDental.entities.Contato;
import com.example.simplesDental.services.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Contato> create(@Valid @RequestBody ContatoDTO contatoDTO) {
        Contato contatoSalvo = contatoService.save(contatoDTO);
        String location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contatoSalvo.getId())
                .toUriString();

        return ResponseEntity.created(URI.create(location)).body(contatoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Contato>> findAll(@RequestParam String q, @RequestParam(required = false) List<String> fields) {
        List<Contato> contatos = contatoService.findAll(q, fields);
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> findById(@PathVariable Long id) {
        Contato contato = contatoService.findById(id);
        return ResponseEntity.ok(contato);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody ContatoDTO contato) {
        Contato updatedContato = contatoService.update(id, contato);
        return ResponseEntity.ok(updatedContato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
