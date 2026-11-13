package com.jcaa.udec.collections.domain.core.exception;

public class RadioYaExisteException extends RuntimeException {
    private static final String MENSAJE_ERROR = "La radio ya existe.";

    public RadioYaExisteException() {
        super(MENSAJE_ERROR);
    }
}
