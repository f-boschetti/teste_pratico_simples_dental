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

/**
 * Controlador REST para gerenciar contatos.
 * Esta classe fornece endpoints para criar, ler, atualizar e excluir contatos.
 */
@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    /**
     * Cria um novo contato com base nos dados fornecidos.
     *
     * @param contatoDTO O DTO que contém os dados do contato a serem salvos.
     * @return Contato criado e o URI do recurso.
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Contato> create(@Valid @RequestBody ContatoDTO contatoDTO) {
        Contato contatoSalvo = contatoService.save(contatoDTO);
        String location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contatoSalvo.getId())
                .toUriString();

        return ResponseEntity.created(URI.create(location)).body(contatoSalvo);
    }

    /**
     * Retorna uma lista de contatos filtrados com base nos critérios fornecidos.
     *
     * @param q A string de busca para filtrar contatos.
     * @param fields Lista de campos a serem retornados.
     * @return Contatos correspondentes aos critérios de busca.
     */
    @GetMapping
    public ResponseEntity<List<Contato>> findAll(@RequestParam String q, @RequestParam(required = false) List<String> fields) {
        List<Contato> contatos = contatoService.findAll(q, fields);
        return ResponseEntity.ok(contatos);
    }

    /**
     * Retorna os detalhes do contato com o ID fornecido.
     *
     * @param id O ID do contato a ser recuperado.
     * @return O contato correspondente ao ID fornecido.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contato> findById(@PathVariable Long id) {
        Contato contato = contatoService.findById(id);
        return ResponseEntity.ok(contato);
    }

    /**
     * Atualiza os dados do contato com o ID fornecido.
     *
     * @param id          O ID do contato a ser atualizado.
     * @param contato  O DTO que contém os novos dados do contato.
     * @return O contato atualizado.
     */
    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody ContatoDTO contato) {
        Contato updatedContato = contatoService.update(id, contato);
        return ResponseEntity.ok(updatedContato);
    }

    /**
     * Realiza a exclusão NÃO lógica do contato com o ID fornecido.
     *
     * @param id O ID do contato a ser excluído.
     * @return Uma resposta sem conteúdo indicando que a exclusão foi realizada.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
