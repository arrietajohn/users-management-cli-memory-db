package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

public enum TipoProduccion {
    PERIODISTICA, COMERCIAL;

    private static final String MENSAJE_ERROR = "El tipo de producción debe ser periodística o comercial.";

    public static TipoProduccion desde(String valor) {
        return Validaciones.enumDesde(TipoProduccion.class, valor, () -> new DatoInvalidoException(MENSAJE_ERROR));
    }
}
