package com.jcaa.udec.collections.domain.core.exception;

public class EmpresaInvalidaException extends RuntimeException {
    public EmpresaInvalidaException() {
        super("Los datos de la empresa no son válidos.");
    }

    public EmpresaInvalidaException(String mensaje) {
        super(mensaje);
    }
}