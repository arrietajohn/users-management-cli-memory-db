package com.jcaa.udec.collections.domain.core.exception;

public class ClienteNoExisteException extends RuntimeException {
    private static final String MENSAJE_ERROR = "El cliente no existe.";

    public ClienteNoExisteException() {
        super(MENSAJE_ERROR);
    }
}
