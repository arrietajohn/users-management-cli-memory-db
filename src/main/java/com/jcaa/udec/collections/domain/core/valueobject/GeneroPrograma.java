package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

public enum GeneroPrograma {
    PERIODISTICO, DEPORTIVO, MUSICAL, CULTURAL, VARIEDADES;

    private static final String MENSAJE_ERROR = "El genero debe ser periodístico, deportivo, musical, cultural o variedades.";

    public static GeneroPrograma desde(String valor) {
        return Validaciones.enumDesde(GeneroPrograma.class, valor, () -> new DatoInvalidoException(MENSAJE_ERROR));
    }
}
