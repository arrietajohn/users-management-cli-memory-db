package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import java.util.Locale;
import java.util.regex.Pattern;

public record Rfc(String valor) {
    private static final Pattern FORMATO = Pattern.compile("^[A-ZÑ&]{3,4}[0-9]{6}[A-Z0-9]{3}$");
    private static final String MENSAJE_ERROR = "El RFC no tiene un formato valido.";

    public Rfc {
        if (Validaciones.esNuloOVacio(valor)) {
            throw new DatoInvalidoException(MENSAJE_ERROR);
        }
        valor = valor.trim().toUpperCase(Locale.ROOT);
        if (!FORMATO.matcher(valor).matches()) {
            throw new DatoInvalidoException(MENSAJE_ERROR);
        }
    }
}
