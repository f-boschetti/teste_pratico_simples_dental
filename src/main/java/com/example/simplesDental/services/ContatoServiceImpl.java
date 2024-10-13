package com.example.simplesDental.services;

import com.example.simplesDental.dto.ContatoDTO;
import com.example.simplesDental.entities.Contato;
import com.example.simplesDental.entities.Profissional;
import com.example.simplesDental.exceptions.ContatoNotFoundException;
import com.example.simplesDental.exceptions.ResourceNotFoundException;
import com.example.simplesDental.repositories.ContatoRepository;
import com.example.simplesDental.repositories.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação da interface {@link ContatoService}.
 * Esta classe fornece a lógica de negócios para as operações relacionadas a contatos.
 */
@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    /**
     * Salva um novo contato com base nos dados fornecidos.
     *
     * @param contatoDTO O DTO que contém os dados do contato a serem salvos.
     * @return O contato salvo.
     * @throws ResourceNotFoundException se o profissional associado não for encontrado.
     */
    @Override
    public Contato save(ContatoDTO contatoDTO) {
        Profissional profissional = profissionalRepository.findById(contatoDTO.getProfissionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Profissional com ID " + contatoDTO.getProfissionalId() + " não encontrado"));

        Contato contato = new Contato();
        contato.setNome(contatoDTO.getNome());
        contato.setContato(contatoDTO.getContato());
        contato.setProfissional(profissional);

        return contatoRepository.save(contato);
    }

    /**
     * Busca uma lista de contatos com base nos critérios de pesquisa fornecidos.
     *
     * @param q A string de pesquisa usada para filtrar contatos pelo nome ou dado de contato.
     * @param fields Lista de campos a serem retornados.
     * @return Uma lista de contatos
     */
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

    /**
     * Retorna o contato correspondente ao ID fornecido.
     *
     * @param id O ID do contato a ser recuperado.
     * @return O contato correspondente ao ID fornecido.
     * @throws ContatoNotFoundException se o contato não for encontrado.
     */
    @Override
    public Contato findById(Long id) {
        return contatoRepository.findById(id).orElseThrow(() -> new ContatoNotFoundException(id));
    }

    /**
     * Atualiza os dados do contato com o ID fornecido.
     *
     * @param id O ID do contato a ser atualizado.
     * @param contatoDTO O DTO que contém os novos dados do contato.
     * @return O contato atualizado.
     * @throws ContatoNotFoundException se o contato não for encontrado.
     * @throws IllegalArgumentException se nenhum campo for fornecido para atualização.
     */
    @Override
    public Contato update(Long id, ContatoDTO contatoDTO) {
        Contato contatoExistente = contatoRepository.findById(id).orElseThrow(() -> new ContatoNotFoundException(id));

        boolean isUpdated = false;

        if (contatoDTO.getNome() != null && !contatoDTO.getNome().isEmpty()) {
            contatoExistente.setNome(contatoDTO.getNome());
            isUpdated = true;
        }
        if (contatoDTO.getContato() != null && !contatoDTO.getContato().isEmpty()) {
            contatoExistente.setContato(contatoDTO.getContato());
            isUpdated = true;
        }

        if (!isUpdated) {
            throw new IllegalArgumentException("Pelo menos um campo deve ser atualizado.");
        }

        return contatoRepository.save(contatoExistente);
    }

    //deveria ser exclusão logica também eu acredito
    /**
     * Realiza a exclusão NÃO lógica do contato com o ID fornecido.
     *
     * @param id O ID do contato a ser excluído.
     * @throws ResourceNotFoundException se o contato não for encontrado.
     */
    @Override
    public void delete(Long id) {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato com ID " + id + " não encontrado"));

        contatoRepository.delete(contato);
    }

    /**
     * Filtra os campos de um contato com base na lista de campos fornecida.
     *
     * @param contato O contato a ser filtrado.
     * @param fields A lista de campos que devem ser incluídos no contato filtrado.
     * @return Um novo objeto Contato contendo apenas os campos especificados.
     */
    protected Contato filterContatoByFields(Contato contato, List<String> fields) {
        Contato filteredContato = new Contato();
        filteredContato.setCreatedDate(null);

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

    @Override
    public void deletarContatos(List<Contato> contatos) {
        contatoRepository.deleteAll(contatos);
    }
}
