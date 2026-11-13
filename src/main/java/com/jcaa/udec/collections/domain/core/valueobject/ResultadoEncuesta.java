package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.exception.EncuestaDescuadradaException;

public record ResultadoEncuesta(int total, int aprobaciones, int rechazos, int indiferencias) {
    private static final double CIEN = 100.0;
    private static final String MENSAJE_NEGATIVAS = "Las cifras de la encuesta no pueden ser negativas.";
    private static final String MENSAJE_TOTAL = "El total de encuestados debe ser mayor que cero.";

    public ResultadoEncuesta {
        if (aprobaciones < 0 || rechazos < 0 || indiferencias < 0) {
            throw new DatoInvalidoException(MENSAJE_NEGATIVAS);
        }
        if (total <= 0) {
            throw new DatoInvalidoException(MENSAJE_TOTAL);
        }
        if (aprobaciones + rechazos + indiferencias != total) {
            throw new EncuestaDescuadradaException();
        }
    }

    public double porcentajeAprobacion() {
        return aprobaciones * CIEN / total;
    }

    public double porcentajeRechazo() {
        return rechazos * CIEN / total;
    }

    public double porcentajeIndiferencia() {
        return indiferencias * CIEN / total;
    }
}
