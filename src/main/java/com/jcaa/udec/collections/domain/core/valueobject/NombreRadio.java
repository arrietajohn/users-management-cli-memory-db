package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.RadioInvalidaException;
import java.util.Objects;

public record NombreRadio(String valor) {
    private static final int LONGITUD_MINIMA = 3;

    public NombreRadio {
        if (Objects.isNull(valor) || valor.isBlank() || valor.length() < LONGITUD_MINIMA) {
            throw new RadioInvalidaException();
        }
    }
}
