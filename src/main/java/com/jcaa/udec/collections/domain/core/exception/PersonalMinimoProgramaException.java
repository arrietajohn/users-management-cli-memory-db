package com.jcaa.udec.collections.domain.core.exception;

public class PersonalMinimoProgramaException extends ExcepcionDominio {
    private static final String MENSAJE_ERROR = "Todo programa debe tener al menos un conductor, un productor y un operador.";

    public PersonalMinimoProgramaException() {
        super(MENSAJE_ERROR);
    }
}
