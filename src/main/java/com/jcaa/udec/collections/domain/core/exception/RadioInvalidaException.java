package com.jcaa.udec.collections.domain.core.exception;

public class RadioInvalidaException extends RuntimeException {
    private static final String MENSAJE_ERROR = "Los datos de la radio son invalidos.";

    public RadioInvalidaException() {
        super(MENSAJE_ERROR);
    }
}
