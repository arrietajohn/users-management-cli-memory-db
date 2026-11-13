package com.jcaa.udec.collections.domain.core.exception;

public class EncuestaDescuadradaException extends ExcepcionDominio {
    private static final String MENSAJE_ERROR = "Las cifras de la encuesta no cierran: el total debe ser igual a aprobaciones + rechazos + indiferencias.";

    public EncuestaDescuadradaException() {
        super(MENSAJE_ERROR);
    }
}
