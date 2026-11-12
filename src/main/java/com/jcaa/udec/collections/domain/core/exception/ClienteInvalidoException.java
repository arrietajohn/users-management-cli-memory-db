package com.jcaa.udec.collections.domain.core.exception;

public class ClienteInvalidoException extends RuntimeException {
    private static final String MENSAJE_ERROR = "Los datos del cliente son invalidos.";

    public ClienteInvalidoException() {
        super(MENSAJE_ERROR);
    }
}
