package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.RadioInvalidaException;
import java.util.Objects;

public record Frecuencia(String valor) {
    public Frecuencia {
        if (Objects.isNull(valor) || valor.isBlank() || !esNumeroPositivo(valor)) {
            throw new RadioInvalidaException();
        }
    }

    private static boolean esNumeroPositivo(String valor) {
        try {
            return Double.parseDouble(valor) > 0;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
