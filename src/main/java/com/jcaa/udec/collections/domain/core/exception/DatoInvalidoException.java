package com.jcaa.udec.collections.domain.core.exception;

/**
 * Excepción de dominio genérica que se lanza cuando un dato de un Value Object
 * o de una entidad no cumple su regla de negocio (por ejemplo, un identificador
 * vacío o un nombre de programa demasiado largo).
 */
public class DatoInvalidoException extends RuntimeException {

    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
