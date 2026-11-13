package com.jcaa.udec.collections.domain.core.exception;

public class UsuarioInvalidoException extends RuntimeException {
    private static final String MENSAJE_ERROR = "Los datos del usuario son inválidos.";

    public UsuarioInvalidoException() {
        super(MENSAJE_ERROR);
    }
}
