package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

public record Consorcio(RadioId radioId, Rfc productoraRfc) {
    private static final String MENSAJE_ERROR = "El consorcio requiere una radio y una compañía productora.";

    public Consorcio {
        if (radioId == null || productoraRfc == null) {
            throw new DatoInvalidoException(MENSAJE_ERROR);
        }
    }
}
