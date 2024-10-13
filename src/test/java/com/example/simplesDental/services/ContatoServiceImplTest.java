package com.example.simplesDental.services;

import com.example.simplesDental.dto.ContatoDTO;
import com.example.simplesDental.entities.Contato;
import com.example.simplesDental.entities.Profissional;
import com.example.simplesDental.exceptions.ContatoNotFoundException;
import com.example.simplesDental.exceptions.ResourceNotFoundException;
import com.example.simplesDental.repositories.ContatoRepository;
import com.example.simplesDental.repositories.ProfissionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContatoServiceImplTest {

    @InjectMocks
    private ContatoServiceImpl contatoService;

    @Mock
    private ContatoRepository contatoRepository;

    @Mock
    private ProfissionalRepository profissionalRepository;

    private ContatoDTO contatoDTO;
    private Contato contato;
    private Profissional profissional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        profissional = new Profissional();
        profissional.setId(1L);

        contatoDTO = new ContatoDTO();
        contatoDTO.setNome("Contato Teste");
        contatoDTO.setContato("123456789");
        contatoDTO.setProfissionalId(profissional.getId());

        contato = new Contato();
        contato.setId(1L);
        contato.setNome(contatoDTO.getNome());
        contato.setContato(contatoDTO.getContato());
        contato.setProfissional(profissional);
    }

    @Test
    public void testSave() {
        when(profissionalRepository.findById(profissional.getId())).thenReturn(Optional.of(profissional));
        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);

        Contato savedContato = contatoService.save(contatoDTO);

        assertNotNull(savedContato);
        assertEquals(contato.getNome(), savedContato.getNome());
        verify(profissionalRepository, times(1)).findById(profissional.getId());
        verify(contatoRepository, times(1)).save(any(Contato.class));
    }

    @Test
    public void testSave_ProfissionalNotFound() {
        when(profissionalRepository.findById(profissional.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> contatoService.save(contatoDTO));
    }

    @Test
    public void testFindAll() {
        when(contatoRepository.findByQuery("Teste")).thenReturn(Arrays.asList(contato));

        List<Contato> contatos = contatoService.findAll("Teste", null);

        assertEquals(1, contatos.size());
        assertEquals(contato.getNome(), contatos.get(0).getNome());
        verify(contatoRepository, times(1)).findByQuery("Teste");
    }

    @Test
    public void testFilterContatoByFields() {
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setNome("Contato Teste");
        contato.setContato("123456789");
        contato.setCreatedDate(LocalDate.now());

        List<String> fieldsNome = Arrays.asList("nome");
        Contato filteredNome = contatoService.filterContatoByFields(contato, fieldsNome);
        assertNull(filteredNome.getId());
        assertEquals("Contato Teste", filteredNome.getNome());
        assertNull(filteredNome.getContato());
        assertNull(filteredNome.getCreatedDate());

        List<String> fieldsIdContato = Arrays.asList("id", "contato");
        Contato filteredIdContato = contatoService.filterContatoByFields(contato, fieldsIdContato);
        assertEquals(1L, filteredIdContato.getId());
        assertNull(filteredIdContato.getNome());
        assertEquals("123456789", filteredIdContato.getContato());
        assertNull(filteredIdContato.getCreatedDate());

        List<String> fieldsAll = Arrays.asList("id", "nome", "contato", "createdDate");
        Contato filteredAll = contatoService.filterContatoByFields(contato, fieldsAll);
        assertEquals(1L, filteredAll.getId());
        assertEquals("Contato Teste", filteredAll.getNome());
        assertEquals("123456789", filteredAll.getContato());
        assertNotNull(filteredAll.getCreatedDate());
    }

    @Test
    public void testFindById() {
        when(contatoRepository.findById(contato.getId())).thenReturn(Optional.of(contato));

        Contato foundContato = contatoService.findById(contato.getId());

        assertNotNull(foundContato);
        assertEquals(contato.getNome(), foundContato.getNome());
        verify(contatoRepository, times(1)).findById(contato.getId());
    }

    @Test
    public void testFindById_ContatoNotFound() {
        when(contatoRepository.findById(contato.getId())).thenReturn(Optional.empty());

        assertThrows(ContatoNotFoundException.class, () -> contatoService.findById(contato.getId()));
    }

    @Test
    public void testUpdate() {
        when(contatoRepository.findById(contato.getId())).thenReturn(Optional.of(contato));
        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);

        contatoDTO.setNome("Novo Nome");
        Contato updatedContato = contatoService.update(contato.getId(), contatoDTO);

        assertNotNull(updatedContato);
        assertEquals("Novo Nome", updatedContato.getNome());
        verify(contatoRepository, times(1)).findById(contato.getId());
        verify(contatoRepository, times(1)).save(contato);
    }

    @Test
    public void testUpdate_ContatoNotFound() {
        when(contatoRepository.findById(contato.getId())).thenReturn(Optional.empty());

        assertThrows(ContatoNotFoundException.class, () -> contatoService.update(contato.getId(), contatoDTO));
    }

    @Test
    public void testDelete() {
        when(contatoRepository.findById(contato.getId())).thenReturn(Optional.of(contato));

        contatoService.delete(contato.getId());

        verify(contatoRepository, times(1)).delete(contato);
    }

    @Test
    public void testDelete_ContatoNotFound() {
        when(contatoRepository.findById(contato.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> contatoService.delete(contato.getId()));
    }
}

