package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.RadioInvalidaException;
import java.util.Objects;

public enum TipoTransmision {
    AM,
    FM;

    public static TipoTransmision desde(String valor) {
        if (Objects.isNull(valor)) {
            throw new RadioInvalidaException();
        }
        try {
            return TipoTransmision.valueOf(valor.trim().toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new RadioInvalidaException();
        }
    }
}
