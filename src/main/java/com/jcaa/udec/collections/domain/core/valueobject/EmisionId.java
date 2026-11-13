package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

public record EmisionId(String valor) {
    private static final String MENSAJE_ERROR = "El id de la emisión debe ser un numero entero positivo.";

    public EmisionId {
        if (Validaciones.esNuloOVacio(valor) || !Validaciones.esEnteroPositivo(valor)) {
            throw new DatoInvalidoException(MENSAJE_ERROR);
        }
    }
}
