package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import java.util.Objects;

public record TelefonoCliente(String valor) {
    private static final int DIGITOS_MINIMOS = 7;
    private static final int DIGITOS_MAXIMOS = 15;
    private static final String PREFIJO_INTERNACIONAL = "+";

    public TelefonoCliente {
        if (!esValido(valor)) {
            throw new ClienteInvalidoException();
        }
    }

    private static boolean esValido(String valor) {
        if (Objects.isNull(valor)) {
            return false;
        }
        String digitos = valor.startsWith(PREFIJO_INTERNACIONAL) ? valor.substring(1) : valor;
        if (digitos.length() < DIGITOS_MINIMOS || digitos.length() > DIGITOS_MAXIMOS) {
            return false;
        }
        for (int indice = 0; indice < digitos.length(); indice++) {
            if (!Character.isDigit(digitos.charAt(indice))) {
                return false;
            }
        }
        return true;
    }
}
