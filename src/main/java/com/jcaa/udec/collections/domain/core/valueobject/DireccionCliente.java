package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import java.util.Objects;

public record DireccionCliente(String valor) {
    private static final int LONGITUD_MINIMA = 5;

    public DireccionCliente {
        if (Objects.isNull(valor) || valor.isBlank() || valor.trim().length() < LONGITUD_MINIMA) {
            throw new ClienteInvalidoException();
        }
    }
}
