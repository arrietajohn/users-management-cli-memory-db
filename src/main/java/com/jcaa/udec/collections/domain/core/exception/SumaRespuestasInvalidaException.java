package com.jcaa.udec.collections.domain.core.exception;

/**
 * Excepción de dominio crítica del CEA: se lanza cuando las respuestas de una
 * encuesta no cuadran (aprobaciones + rechazos + indiferencias distinto del
 * total de encuestados) o cuando alguno de los valores es inválido.
 */
public class SumaRespuestasInvalidaException extends RuntimeException {

    public SumaRespuestasInvalidaException(String mensaje) {
        super(mensaje);
    }
}
