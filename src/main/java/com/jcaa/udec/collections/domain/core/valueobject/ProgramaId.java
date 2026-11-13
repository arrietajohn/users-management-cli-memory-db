package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

public record ProgramaId(String valor) {
    private static final String MENSAJE_ERROR = "El id del programa debe ser un numero entero positivo.";

    public ProgramaId {
        if (Validaciones.esNuloOVacio(valor) || !Validaciones.esEnteroPositivo(valor)) {
            throw new DatoInvalidoException(MENSAJE_ERROR);
        }
    }
}
