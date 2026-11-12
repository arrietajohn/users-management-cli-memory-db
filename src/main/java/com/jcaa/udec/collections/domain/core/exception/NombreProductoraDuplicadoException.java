package com.jcaa.udec.collections.domain.core.exception;

public class NombreProductoraDuplicadoException extends ExcepcionDominio {
    private static final String MENSAJE_ERROR = "Ya existe una compañía productora con ese nombre.";

    public NombreProductoraDuplicadoException() {
        super(MENSAJE_ERROR);
    }
}
