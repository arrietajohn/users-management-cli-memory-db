package com.jcaa.udec.collections.domain.core.exception;

public abstract class ExcepcionDominio extends RuntimeException {
    protected ExcepcionDominio(String mensaje) {
        super(mensaje);
    }
}
