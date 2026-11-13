package com.jcaa.udec.collections.domain.core.exception;

public class RadioNoEmiteProgramaException extends ExcepcionDominio {
    private static final String MENSAJE_ERROR = "La radio no emite este programa.";

    public RadioNoEmiteProgramaException() {
        super(MENSAJE_ERROR);
    }
}
