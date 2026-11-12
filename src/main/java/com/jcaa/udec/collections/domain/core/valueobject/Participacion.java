package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

public record Participacion(Cedula cedula, RolPersona rol, TipoProduccion tipoProduccion) {
    private static final String MENSAJE_DATOS = "La participación requiere cedula y rol.";
    private static final String MENSAJE_PRODUCTOR = "Un productor debe indicar si su producción es periodística o comercial.";
    private static final String MENSAJE_NO_PRODUCTOR = "Solo un productor tiene tipo de producción.";

    public Participacion {
        if (cedula == null || rol == null) {
            throw new DatoInvalidoException(MENSAJE_DATOS);
        }
        if (rol == RolPersona.PRODUCTOR && tipoProduccion == null) {
            throw new DatoInvalidoException(MENSAJE_PRODUCTOR);
        }
        if (rol != RolPersona.PRODUCTOR && tipoProduccion != null) {
            throw new DatoInvalidoException(MENSAJE_NO_PRODUCTOR);
        }
    }

    public static Participacion conductor(Cedula cedula) {
        return new Participacion(cedula, RolPersona.CONDUCTOR, null);
    }

    public static Participacion operador(Cedula cedula) {
        return new Participacion(cedula, RolPersona.OPERADOR, null);
    }

    public static Participacion productor(Cedula cedula, TipoProduccion tipoProduccion) {
        return new Participacion(cedula, RolPersona.PRODUCTOR, tipoProduccion);
    }
}
