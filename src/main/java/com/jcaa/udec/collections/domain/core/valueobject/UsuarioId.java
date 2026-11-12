package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.UsuarioInvalidoException;
import java.util.Objects;

public record UsuarioId(String valor) {
    public UsuarioId {
        if (Objects.isNull(valor) || valor.isBlank() || !esNumeroEntero(valor)) {
            throw new UsuarioInvalidoException();
        }
    }

    private static boolean esNumeroEntero(String valor) {
        try {
            Long.parseLong(valor);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
