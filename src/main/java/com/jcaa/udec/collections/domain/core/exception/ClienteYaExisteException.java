package com.jcaa.udec.collections.domain.core.exception;

public class ClienteYaExisteException extends RuntimeException {
    private static final String MENSAJE_ERROR = "El cliente ya existe.";

    public ClienteYaExisteException() {
        super(MENSAJE_ERROR);
    }
}
