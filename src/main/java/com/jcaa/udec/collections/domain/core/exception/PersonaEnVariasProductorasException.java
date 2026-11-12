package com.jcaa.udec.collections.domain.core.exception;

public class PersonaEnVariasProductorasException extends ExcepcionDominio {
    private static final String MENSAJE_ERROR = "Una persona no puede trabajar en mas de una compania productora.";

    public PersonaEnVariasProductorasException() {
        super(MENSAJE_ERROR);
    }
}
