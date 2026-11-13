package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.RadioInvalidaException;
import java.util.Objects;

public record RadioId(String valor) {
    public RadioId {
        if (Objects.isNull(valor) || valor.isBlank() || !esNumeroEntero(valor)) {
            throw new RadioInvalidaException();
        }
    }

    private static boolean esNumeroEntero(String valor) {
        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
