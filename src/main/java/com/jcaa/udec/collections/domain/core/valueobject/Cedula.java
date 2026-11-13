package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

public record Cedula(String valor) {
    private static final String FORMATO = "[0-9]{6,12}";
    private static final String MENSAJE_ERROR = "La cedula debe tener entre 6 y 12 dígitos.";

    public Cedula {
        if (Validaciones.esNuloOVacio(valor) || !valor.trim().matches(FORMATO)) {
            throw new DatoInvalidoException(MENSAJE_ERROR);
        }
        valor = valor.trim();
    }
}
