package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EmpresaInvalidaException;
import java.util.Objects;

public record EmpresaId(String valor) {
    public EmpresaId {
        if (Objects.isNull(valor) || valor.isBlank()) {
            throw new EmpresaInvalidaException("El identificador de la empresa no puede ser nulo ni vacío.");
        }
    }
}