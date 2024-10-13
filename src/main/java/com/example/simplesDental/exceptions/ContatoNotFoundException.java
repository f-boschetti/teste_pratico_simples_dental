package com.example.simplesDental.exceptions;

public class ContatoNotFoundException extends RuntimeException {

    public ContatoNotFoundException(Long id) {
        super("Contato não encontrado: " + id);
    }

}
