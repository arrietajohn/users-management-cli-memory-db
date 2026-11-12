package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import java.util.Objects;

public record CodigoCliente(String valor) {
    private static final int LONGITUD_MINIMA = 3;
    private static final int LONGITUD_MAXIMA = 20;
    private static final char GUION = '-';

    public CodigoCliente {
        if (!esValido(valor)) {
            throw new ClienteInvalidoException();
        }
    }

    private static boolean esValido(String valor) {
        if (Objects.isNull(valor)
                || valor.length() < LONGITUD_MINIMA
                || valor.length() > LONGITUD_MAXIMA) {
            return false;
        }
        for (int indice = 0; indice < valor.length(); indice++) {
            char caracter = valor.charAt(indice);
            if (!Character.isLetterOrDigit(caracter) && caracter != GUION) {
                return false;
            }
        }
        return true;
    }
}
