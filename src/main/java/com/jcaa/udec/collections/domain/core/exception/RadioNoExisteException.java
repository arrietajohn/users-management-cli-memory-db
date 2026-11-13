package com.jcaa.udec.collections.domain.core.exception;

public class RadioNoExisteException extends RuntimeException {
    private static final String MENSAJE_ERROR = "La radio no existe.";

    public RadioNoExisteException() {
        super(MENSAJE_ERROR);
    }
}
