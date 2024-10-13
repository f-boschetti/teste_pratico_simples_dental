package com.example.simplesDental.services;

import com.example.simplesDental.dto.ContatoDTO;
import com.example.simplesDental.entities.Contato;

import java.util.List;

/**
 * Interface que define os contratos para as operações relacionadas a contatos.
 * Métodos para criar, ler, atualizar e excluir contatos.
 */
public interface ContatoService {

    /**
     * Salva um novo contato com base nos dados fornecidos.
     *
     * @param contato O DTO que contém os dados do contato a serem salvos.
     * @return O contato salvo.
     */
    Contato save(ContatoDTO contato);

    /**
     * Busca uma lista de contatos
     *
     * @param q A string de pesquisa usada para filtrar contatos pelo nome ou dado de contato.
     * @param fields Lista de campos a serem retornados.
     * @return Uma lista de contatos
     */
    List<Contato> findAll(String q, List<String> fields);

    /**
     * Retorna o contato correspondente ao ID fornecido.
     *
     * @param id O ID do contato a ser recuperado.
     * @return O contato correspondente ao ID fornecido.
     */
    Contato findById(Long id);

    /**
     * Atualiza os dados do contato com o ID fornecido.
     *
     * @param id O ID do contato a ser atualizado.
     * @param contato O DTO que contém os novos dados do contato.
     * @return O contato atualizado.
     */
    Contato update(Long id, ContatoDTO contato);

    /**
     * Realiza a exclusão NÃO lógica do contato com o ID fornecido.
     *
     * @param id O ID do contato a ser excluído.
     */
    void delete(Long id);
}
