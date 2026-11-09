package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EmpresaInvalidaException;
import java.util.Objects;

public record NombreEmpresa(String valor) {
    private static final int LONGITUD_MINIMA = 3;

    public NombreEmpresa {
        if (Objects.isNull(valor) || valor.isBlank() || valor.trim().length() < LONGITUD_MINIMA) {
            throw new EmpresaInvalidaException("El nombre de la empresa debe tener al menos " + LONGITUD_MINIMA + " caracteres.");
        }
    }
}