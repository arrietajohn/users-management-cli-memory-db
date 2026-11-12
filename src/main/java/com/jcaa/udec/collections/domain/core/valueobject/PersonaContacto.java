package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import java.util.Objects;

public record PersonaContacto(String valor) {
    private static final int LONGITUD_MINIMA = 3;

    public PersonaContacto {
        if (Objects.isNull(valor) || valor.isBlank() || valor.trim().length() < LONGITUD_MINIMA) {
            throw new ClienteInvalidoException();
        }
    }
}
