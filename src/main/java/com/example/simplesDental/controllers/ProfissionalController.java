package com.example.simplesDental.controllers;

import com.example.simplesDental.dto.ProfissionalDTO;
import com.example.simplesDental.entities.Profissional;
import com.example.simplesDental.services.ProfissionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @GetMapping
    public ResponseEntity<List<Profissional>> findAll(@RequestParam(required = false) String q, @RequestParam(required = false) List<String> fields) {
        List<Profissional> profissionais = profissionalService.findAll(q, fields);
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> findById(@PathVariable Long id) {
        return ResponseEntity.ok(profissionalService.findById(id));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Profissional> create(@Valid @RequestBody ProfissionalDTO profissionalDTO) {
        Profissional save = profissionalService.save(profissionalDTO);

        String location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUriString();

        return ResponseEntity.created(URI.create(location)).body(save);
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public ResponseEntity<Profissional> update(@PathVariable Long id, @Valid @RequestBody ProfissionalDTO profissionalDTO) {
        return ResponseEntity.ok(profissionalService.update(id, profissionalDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        profissionalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
