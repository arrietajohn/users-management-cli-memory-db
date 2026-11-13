package com.jcaa.udec.collections.domain.core.valueobject;

import java.text.Normalizer;
import java.util.Locale;
import java.util.function.Supplier;

final class Validaciones {
    private Validaciones() {
    }

    static boolean esNuloOVacio(String valor) {
        return valor == null || valor.isBlank();
    }

    static boolean esEnteroPositivo(String valor) {
        try {
            return Integer.parseInt(valor) > 0;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    static <E extends Enum<E>> E enumDesde(
            Class<E> tipo, String valor, Supplier<? extends RuntimeException> error) {
        if (esNuloOVacio(valor)) {
            throw error.get();
        }
        String normalizado = Normalizer.normalize(valor.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toUpperCase(Locale.ROOT);
        for (E constante : tipo.getEnumConstants()) {
            if (constante.name().equals(normalizado)) {
                return constante;
            }
        }
        throw error.get();
    }
}
