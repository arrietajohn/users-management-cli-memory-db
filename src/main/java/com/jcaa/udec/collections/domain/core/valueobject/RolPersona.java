package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

public enum RolPersona {
    CONDUCTOR, OPERADOR, PRODUCTOR;

    private static final String MENSAJE_ERROR = "El rol debe ser conductor, operador o productor.";

    public static RolPersona desde(String valor) {
        return Validaciones.enumDesde(RolPersona.class, valor, () -> new DatoInvalidoException(MENSAJE_ERROR));
    }
}
