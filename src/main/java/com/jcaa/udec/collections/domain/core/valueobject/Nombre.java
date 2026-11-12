package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

public record Nombre(String valor) {
    private static final int LONGITUD_MINIMA = 3;
    private static final String MENSAJE_ERROR = "El nombre debe tener mínimo 3 caracteres.";

    public Nombre {
        if (Validaciones.esNuloOVacio(valor) || valor.trim().length() < LONGITUD_MINIMA) {
            throw new DatoInvalidoException(MENSAJE_ERROR);
        }
        valor = valor.trim();
    }
}
