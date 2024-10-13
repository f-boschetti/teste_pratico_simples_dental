package com.example.simplesDental.anotations;

import com.example.simplesDental.enums.Cargo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Classe responsável por validar o valor do cargo em um DTO.
 * Implementa a interface {@link ConstraintValidator} para fornecer
 * uma lógica de validação personalizada para o enum {@link Cargo}.
 */
public class CargoValidator implements ConstraintValidator<ValidCargo, Cargo> {

    /**
     * Valida se o cargo fornecido não é nulo e se corresponde a um valor do enum {@link Cargo}.
     *
     * @param cargo   O valor do cargo a ser validado.
     * @return {@code true} se o cargo não for nulo e for um valor válido do enum,
     *         {@code false} caso contrário.
     */

    @Override
    public boolean isValid(Cargo cargo, ConstraintValidatorContext context) {
        return cargo != null && Cargo.valueOf(cargo.name()) != null;
    }
}